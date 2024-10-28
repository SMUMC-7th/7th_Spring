package com.example.umc7th.article.controller;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

// RestController 명시
@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입)
@RequiredArgsConstructor
@Tag(name = "article 관련 컨트롤러")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 api", description = "article 생성하는 API")
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }

    // 생성이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
    @GetMapping("/articles/{articleId}")
    @Operation(summary = "특정 article을 조회하는 API", description = "articleId를 이용하여 article을 조회하는 용도이며 조회한 내용 반환")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        // 구현
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @GetMapping("/articles")
    @Operation(summary = "모든 article을 조회하는 API", description = "작성된 모든 article을 조회하는 용도이며 조회한 내용들을 리스트로 반환")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    @PatchMapping("/articles/{articleId}")
    @Operation(summary = "article 수정 API", description = "article을 수정하는 api")
    public CustomResponse<ArticleResponseDTO.UpdateArticleResponseDTO> updateArticle(@PathVariable("articleId") Long articleId, @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        Article updatedArticle = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.UpdateArticleResponseDTO.from(updatedArticle));
    }

    @DeleteMapping("/articles/{articleId}")
    @Operation(summary = "article 삭제 API", description = "article을 삭제하는 api")
    public CustomResponse<ArticleResponseDTO.DeleteArticleResponseDTO> deleteArticle(@PathVariable("articleId") Long articleId) {
        Article deleteArticle = articleQueryService.getArticle(articleId);
        articleCommandService.deleteArticle(deleteArticle.getId());
        return CustomResponse.onSuccess(ArticleResponseDTO.DeleteArticleResponseDTO.from(deleteArticle));
    }

    @PatchMapping("/articles/{articleId}/addLikeNum")
    @Operation(summary = "좋아요 중가 API", description = "article의 좋아요 증가시키는 api")
    public CustomResponse<ArticleResponseDTO.LikeNumResponseDTO> addArticleLikeNum(@PathVariable("articleId") Long articleId) {
        Article updatedArticle = articleCommandService.updateLikeNum(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.LikeNumResponseDTO.from(updatedArticle));
    }

    @GetMapping("/articles/{articleId}/hasReplies")
    @Operation(summary = "댓글 존재 여부 확인 API", description = "article에 댓글이 존재하는지 확인하는 api")
    public CustomResponse<Boolean> hasReplies(@PathVariable("articleId") Long articleId) {
        boolean hasReplies = articleQueryService.hasReplies(articleId);
        return CustomResponse.onSuccess(hasReplies);
    }

    @GetMapping("/articles/page")
    @Operation(summary = "커서 기반 페이지네이션 API", description = "article 커서 기반 페이지네이션 API")
    public CustomResponse<ArticleResponseDTO.ArticlePagePreviewListDTO> getArticlesByCursor(
            @RequestParam(value = "cursor", required = false)LocalDateTime cursor,
            @RequestParam(value = "size", defaultValue = "3") int size) {

        LocalDateTime cursorToUse = (cursor == null) ? LocalDateTime.now() : cursor;
        Slice<Article> articles = articleQueryService.getArticlesByCursor(cursorToUse, size);

        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePagePreviewListDTO.from(articles));
    }
}
