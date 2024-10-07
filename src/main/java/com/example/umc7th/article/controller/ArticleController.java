package com.example.umc7th.article.controller;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryServiceImpl;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryServiceImpl articleQueryService;
    private final ArticleCommandService articleCommandService;
    private final ArticleRepository articleRepository;

    @PostMapping("/articles")
    public CustomResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto){
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(article);
    }

    @GetMapping("/articles/{articleId}")
    public CustomResponse<Optional<Article>> getArticle(@PathVariable("articleId") Long articleId){
        Optional<Article> article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(article);
    }

    @GetMapping("/articles")
    public CustomResponse<List<Article>> getArticles(Long id){
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(articles);
    }

}
