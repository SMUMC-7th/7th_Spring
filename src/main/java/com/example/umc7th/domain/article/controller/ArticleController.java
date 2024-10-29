package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입)
@RequiredArgsConstructor
@RequestMapping("/articles")
@Tag(name = "게시글 API")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping
    @Operation(summary = "게시글 생성 API")
    // 요청 시 데이터를 담을 DTO 를 설정해주고 RequestBody 라는 것을 명시
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // CustomResponse에 article을 담아 성공했다고 응답하기
        return CustomResponse.onSuccess(ArticleConverter.toCreateArticleResponseDTO(article));
    }

    @GetMapping
    @Operation(summary = "전체 게시글 조회 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(ArticleConverter.toArticlePreviewListDTO(articles));
    }

    // 생성이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정
    @GetMapping("{articleId}")
    @Operation(summary = "단일 게시글 조회 API")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기
    // GET method는 RequestBody 사용이 불가능
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleConverter.toArticlePreviewDTO(article));
    }

    @PutMapping
    @Operation(summary = "게시글 수정 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(@RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleCommandService.updateArticle(dto);
        return CustomResponse.onSuccess(ArticleConverter.toArticlePreviewDTO(article));
    }

    @DeleteMapping("{articleId}")
    @Operation(summary = "게시글 삭제 API")
    public CustomResponse<ArticleResponseDTO.DeletedArticleDTO> deleteArticle(@PathVariable("articleId") Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(ArticleConverter.toDeletedArticleDTO(articleId));
    }

    @PatchMapping("{articleId}")
    @Operation(summary = "게시글 좋아요 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> patchArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleCommandService.patchArticle(articleId);
        return CustomResponse.onSuccess(ArticleConverter.toArticlePreviewDTO(article));
    }

    @GetMapping("{articleId}/checkReplies")
    @Operation(summary = "댓글 존재 여부 확인 API")
    public CustomResponse<Boolean> checkReplies(@PathVariable("articleId") Long articleId) {
        return CustomResponse.onSuccess(articleQueryService.hasComments(articleId));
    }
}