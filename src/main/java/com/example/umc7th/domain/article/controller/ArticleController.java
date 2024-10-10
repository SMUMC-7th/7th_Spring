package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
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
@RequiredArgsConstructor //생성자 의존성 주입
@RequestMapping("/articles")
@Tag(name = "게시글 API")
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("")
    @Operation(method ="POST", summary = "게시글 생성 API", description = "게시글 생성하는 API")
    //요청 시 데이터 담을 DTO 명시, RequestBody 명시
    public ResponseEntity<CustomResponse<ArticleResponseDTO.CreateArticleResponseDto>> createArticle(@RequestBody ArticleRequestDTO.CreateArticleRequestDTO dto){
        ArticleResponseDTO.CreateArticleResponseDto responseDto = articleCommandService.createArticle(dto);
        //성공 응답
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED,responseDto));
    }

    //게시글 전체 조회
    @GetMapping("")
    @Operation(method ="GET", summary = "게시글 전체 조회 API", description = "게시글 전체 조회하는 API")
    public CustomResponse<List<ArticleResponseDTO.CreateArticleResponseDto>> getArticles(){
        List<ArticleResponseDTO.CreateArticleResponseDto> articles = articleQueryService.getArticleList();
        return CustomResponse.onSuccess(articles);
    }

    //게시글 하나 조회
    @GetMapping("/{articleId}")
    @Operation(method ="GET", summary = "단일 게시판 조회 API", description = "단일 게시글 조회하는 API")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDto> getArticle(@PathVariable("articleId") Long articleId){
        ArticleResponseDTO.CreateArticleResponseDto responseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(responseDto);
    }
}
