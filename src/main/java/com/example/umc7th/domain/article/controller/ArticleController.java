package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시글 API")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    /**
     * 게시글 생성 API
     * @param dto (게시글 생성 요청 정보를 담은 DTO)
     * @return 게시글 생성 후 성공 응답을 CustomResponse 형태로 반환
     */
    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // 전달받은 DTO로 게시글 생성
        Article article = articleCommandService.createArticle(dto);
        // 생성된 게시글 정보를 담은 DTO를 CustomResponse로 래핑하여 성공 응답 반환
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }


    /**
     * 특정 게시글 조회 API
     *
     * @param articleId (조회하려는 게시글의 Id)
     * @return 게시글 조회 후 성공 응답을 CustomResponse 형태로 반환
     */
    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 조회 API", description = "게시글 하나 조회하는 API")
    public CustomResponse<Optional<ArticleResponseDTO.ArticlePreviewDTO>> getArticle(@PathVariable("articleId") Long articleId) {
        // PathVariable로 전달받은 게시글 ID를 이용해 해당 게시글 조회
        Optional<Article> article = articleQueryService.getArticle(articleId);
        // 조회된 게시글 정보를 담은 DTO를 CustomResponse로 래핑하여 성공 응답 반환
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    /**
     * 모든 게시글 조회 API
     * @return 모든 게시글을 조회한 후 성공 응답을 CustomResponse 형태로 반환
     */
    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회 API", description = "게시글 전체 조회하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
        // 데이터베이스에서 모든 게시글 조회
        List<Article> articles = articleQueryService.getArticles();
        // 조회된 모든 게시글 정보를 담은 DTO 리스트를 CustomResponse로 래핑하여 성공 응답 반환
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    /**
     * 게시글 수정 API (PUT - 전체 수정)
     * @param articleId (수정할 게시글의 Id)
     * @param dto (수정할 게시글 정보)
     * @return 수정된 게시글 정보를 담은 DTO를 CustomResponse로 반환
     */
    @PutMapping("/articles/{articleId}")
    @Operation(summary = "게시글 전체 수정 API", description = "게시글 전체 수정하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticlePut(@PathVariable("articleId") Long articleId,
                                                                                 @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        // 게시글 수정
        Article updatedArticle = articleCommandService.updateArticle(articleId, dto);
        // 성공 응답 반환
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(Optional.of(updatedArticle)).get());
    }

    /**
     * 게시글 삭제 API
     *
     * @param articleId (삭제할 게시글의 Id)
     * @return 성공 응답을 CustomResponse 형태로 반환
     */
    @DeleteMapping("/articles/{articleId}")
    @Operation(summary = "게시글 삭제 API", description = "게시글 삭제하는 API")
    public CustomResponse<String> deleteArticle(@PathVariable("articleId") Long articleId) {
        // 게시글 삭제
        articleCommandService.deleteArticle(articleId);
        // 성공 응답 반환
        return CustomResponse.onSuccess("게시글 삭제가 성공적으로 완료되었습니다.");
    }

}