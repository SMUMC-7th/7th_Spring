package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleCommandService articleCommandService;
    private final ArticleQueryService articleQueryService;

    @PostMapping("")
    public ResponseEntity<CustomResponse<ArticleResDto.CreateArticleResponseDto>> createArticle(
            @RequestBody ArticleReqDto.CreateArticleRequestDto requestDto) {

        ArticleResDto.CreateArticleResponseDto responseDto = articleCommandService.createArticle(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    @GetMapping("")
    public CustomResponse<List<ArticleResDto.CreateArticleResponseDto>> getArticleList() {
        List<ArticleResDto.CreateArticleResponseDto> articles = articleQueryService.getArticleList();
        return CustomResponse.onSuccess(articles);
    }

    @GetMapping("/{articleId}")
    public CustomResponse<ArticleResDto.CreateArticleResponseDto> getArticle(@PathVariable Long articleId) {
        ArticleResDto.CreateArticleResponseDto responseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(responseDto);
    }
}
