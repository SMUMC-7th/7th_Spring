package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시글 API") // Swagger에 표시될 API 그룹 이름
public class ArticleController {

    // 게시글 조회 관련 서비스
    private final ArticleQueryService articleQueryService;
    // 게시글 생성, 수정, 삭제 관련 서비스
    private final ArticleCommandService articleCommandService;
    private final ArticleRepository articleRepository;

    /** 게시물 생성 API */
    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 API", description = "게시글 생성하는 API") // Swagger 설명
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }

    /** 게시물 한개 조회 API */
    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 조회 API", description = "게시글 하나 조회하는 API") // Swagger 설명
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }


    /** 게시물 전체 조회 API
     * query가 "LIKE"일 경우:
     *   좋아요 수를 기준으로 게시글을 정렬하여 조회
     *   좋아요 수가 같다면 게시물 ID를 기준으로 추가 정렬이 이루어집니다.
     * query가 "ID"일 경우:
     *   게시글을 ID 순서로 조회합니다.
     * */

    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회 API", description = "게시글 전체 조회하는 API") // Swagger 설명
    @Parameters({
            // 페이지네이션에 사용되는 커서 값
            @Parameter(name = "cursor", description = "커서 값, 처음이면 0"), // 커서 값 parameter 설명
            // LIKE <- 필터링된 조회를 포함한 전체 조회 API로 활용하기 위해
            @Parameter(name = "query", description = "쿼리 LIKE, ID") // 쿼리 방식 parameter 설명
    })
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles(@RequestParam(value = "query", defaultValue = "LIKE")String query,
                                                                                @RequestParam("cursor") Long cursor,
                                                                                //페이지네이션에서 한번에 몇 개의 게시글을 조회할지
                                                                                @RequestParam(value = "offset", defaultValue = "10") Integer offset) {
        Slice<Article> articles = articleQueryService.getArticles(query, cursor, offset);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    /** 게시물 수정 API */
    @PutMapping("/articles/{articleId}")
    @Operation(summary = "게시글 수정 API", description = "게시글 수정하는 API") // Swagger 설명
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(@PathVariable("articleId") Long articleId,
                                                                              @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    /** 게시물 좋아요 수 증가 API */
    @PatchMapping("/articles/{articleId}")
    @Operation(summary = "좋아요 수 증가 API", description = "게시글 좋아요 수 증가 API") // Swagger 설명
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> increaseLike(@PathVariable("articleId") Long articleId) {
        Article article = articleCommandService.increaseLike(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    /** 게시물 삭제 API */
    @DeleteMapping("/articles/{articleId}")
    @Operation(summary = "게시글 삭제 API", description = "게시글 삭제하는 API") // Swagger 설명
    public CustomResponse<Void> deleteArticle(@PathVariable("articleId") Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(null);
    }



}