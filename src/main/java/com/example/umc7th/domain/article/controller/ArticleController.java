package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시글 API")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {

        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }

    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 조회 API", description = "게시글 하나 조회하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {

        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회 API", description = "게시글 전체를 조회하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {

        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    @PutMapping("/articles/{articleId}")
    @Operation(summary = "게시글 업데이트 API", description = "게시글 업데이트 하는 API")
    public CustomResponse<ArticleResponseDTO.ArticleUpdateDTO> updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {

        // 새로운 content 업데이트 필요
        Article article = articleCommandService.updateArticle(articleId, dto);

        return CustomResponse.onSuccess(ArticleResponseDTO.ArticleUpdateDTO.from(article));
    }

    @DeleteMapping("/articles/{articleId}")
    @Operation(summary = "게시글 삭제 API", description = "게시글 삭제하는 API")
    public CustomResponse<?> deleteArticle(@PathVariable("articleId") Long articleId) {

        articleCommandService.deleteArticle(articleId);

        return CustomResponse.onSuccess("articleId = " + articleId + "삭제했습니다.");
    }

    @GetMapping("/articles/{articleId}/hasReply")
    @Operation(summary = "댓글 존재 여부 확인 API", description = "댓글 존재 여부 확인")
    public CustomResponse<Boolean> hasReply(@PathVariable("articleId") Long articleId) {

        return CustomResponse.onSuccess(articleQueryService.hasReply(articleId));
    }

    @GetMapping("/articles/cursorPage")
    @Operation(summary = "커서 기반 페이지네이션 API", description = "커서 기반 페이지네이션")
    public CustomResponse<ArticleResponseDTO.ArticlePagePreviewListDTO> getArticlesByCursor(
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "7") int size) {

        Slice<Article> articles = articleQueryService.getArticlesByCursor(cursorId, size);

        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePagePreviewListDTO.from(articles));
    }
}
