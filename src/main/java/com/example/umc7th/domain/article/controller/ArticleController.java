package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;


    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 API", description="게시글 생성")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }
    //특정 게시물 조회
    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 조회 API", description="게시글 단일 조회")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }
    //모든 게시물 조회
    @GetMapping("/articles")
    @Operation(summary = "게시글 조회 API", description="게시글 전체 조회")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }
}
