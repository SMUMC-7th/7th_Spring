package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "게시글 관련 API", description = "게시글 관련 API입니다.")
public class ArticleController {

    private final ArticleCommandService articleCommandService;
    private final ArticleQueryService articleQueryService;

    @Operation(summary = "게시글 작성", description = "request로 넘긴 title과 content로 게시글을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<CustomResponse<ArticleResDto.CreateArticleResponseDto>> createArticle(
            @RequestBody ArticleReqDto.CreateArticleRequestDto requestDto) {

        ArticleResDto.CreateArticleResponseDto responseDto = articleCommandService.createArticle(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    @Operation(summary = "전체 게시글 리스트 조회", description = "전체 게시글을 조회합니다.")
    @GetMapping("")
    public CustomResponse<List<ArticleResDto.CreateArticleResponseDto>> getArticleList() {
        List<ArticleResDto.CreateArticleResponseDto> articles = articleQueryService.getArticleList();
        return CustomResponse.onSuccess(articles);
    }

    @Operation(summary = "게시글 단일 조회", description = "게시글의 ID로 게시글을 단일 조회합니다.")
    @GetMapping("/{articleId}")
    public CustomResponse<ArticleResDto.CreateArticleResponseDto> getArticle(@PathVariable Long articleId) {
        ArticleResDto.CreateArticleResponseDto responseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(responseDto);
    }
}
