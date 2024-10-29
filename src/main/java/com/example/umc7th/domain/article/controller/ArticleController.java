package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;


    @PostMapping("")
    @Operation(summary = "게시글 생성 API", description="게시글 생성")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }
    //특정 게시물 조회
    @GetMapping("/{articleId}")
    @Operation(summary = "게시글 조회 API", description="게시글 단일 조회")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }
    //모든 게시물 조회(페이지네이션x=>false,false넘겨주기)
    @GetMapping("")
    @Operation(summary = "게시글 조회 API", description="게시글 전체 조회")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles,false,false));
    }
    //게시물 수정
    @PutMapping("{articleId}")
    @Operation(summary = "게시글 수정 API", description="게시글 수정")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {

        Article updatedArticle = articleCommandService.updateArticle(articleId, dto);
        ArticleResponseDTO.ArticlePreviewDTO responseDto = ArticleResponseDTO.ArticlePreviewDTO.from(updatedArticle);

        return CustomResponse.onSuccess(responseDto);
    }
    @DeleteMapping("/{articleId}")
    public CustomResponse<Void> deleteArticle(@PathVariable Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(null);
    }

    //댓글 o 게시물 확인
    @GetMapping("/{articleId}/hasReply")
    @Operation(summary = "게시글 댓글 존재 확인 API", description="게시글 댓글 존재 확인")
    public CustomResponse<Boolean> hasReply(@PathVariable("articelId") Long articleId){
        boolean hasReply = articleQueryService.hasReply(articleId);
        return CustomResponse.onSuccess(hasReply);
    }

    //id기준 커서기반 페이지네이션
    @GetMapping
    @Operation(summary = "게시글 페이지네이션", description = "커서 기반 게시글 페이지네이션")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticlesAfterCursor(
            @RequestParam(required = false) Long cursorId,
            Pageable pageable) {

        // 게시글 가져오기
        Slice<Article> articles = articleQueryService.getArticlesAfterCursorById(cursorId, pageable);

        // DTO 변환
        ArticleResponseDTO.ArticlePreviewListDTO responseDTO = ArticleResponseDTO.ArticlePreviewListDTO.from(
                articles.getContent(),
                articles.hasNext(),
                articles.hasPrevious()
        );

        return CustomResponse.onSuccess(responseDTO);
    }

}
