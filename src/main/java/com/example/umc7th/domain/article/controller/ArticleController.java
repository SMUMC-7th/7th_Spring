package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
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
@RequiredArgsConstructor //생성자 의존성 주입
@Tag(name = "게시판 API")
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    @Operation(method ="POST", summary = "게시판 생성 API")
    //요청 시 데이터 담을 DTO 명시, RequestBody 명시
    public CustomResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleRequestDTO dto){
        //service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        //성공 응답
        return CustomResponse.onSuccess(article);
    }

    //게시글 전체 조회
    @GetMapping("/articles")
    @Operation(method ="GET", summary = "게시판 전체 조회 API")
    public CustomResponse<List<Article>> getArticles(){
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(articles);
    }

    //게시글 하나 조회
    @GetMapping("articles/{articleId}")
    @Operation(method ="GET", summary = "단일 게시판 조회 API")
    public CustomResponse<Article> getArticle(@PathVariable("articleId") Long articleId){
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(article);
    }
}
