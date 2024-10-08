package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;


    @PostMapping("/articles")
    public CustomResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(article);
    }
    //특정 게시물 조회
    @GetMapping("/articles/{articleId}")
    public CustomResponse<Article> getArticle(@PathVariable("articleId") Long articleId) {
        Article article=articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(article);
    }
    //모든 게시물 조회
    @GetMapping("/articles")
    public CustomResponse<List<Article>> getArticles() {
        List<Article> articles=articleQueryService.getArticles();
        return CustomResponse.onSuccess(articles);
    }
}
