package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    @Operation(method = "POST", summary = "게시글 생성 API", description = "게시글 생성하는 API")
    //요청 시 데이터 담을 DTO 명시, RequestBody 명시
    public ResponseEntity<CustomResponse<ArticleResponseDTO.ArticlePreviewDTO>> createArticle(@RequestBody ArticleRequestDTO.CreateArticleRequestDTO dto) {
        ArticleResponseDTO.ArticlePreviewDTO responseDto = articleCommandService.createArticle(dto);
        //성공 응답
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CustomResponse.onSuccess(HttpStatus.CREATED, responseDto));
    }

    //게시글 전체 조회
    @GetMapping("")
    @Operation(method = "GET", summary = "게시글 전체 조회 API", description = "게시글 전체 조회하는 API")
    public CustomResponse<List<ArticleResponseDTO.ArticlePreviewDTO>> getArticles() {
        List<ArticleResponseDTO.ArticlePreviewDTO> articles = articleQueryService.getArticleList();
        return CustomResponse.onSuccess(articles);
    }

    //게시글 하나 조회
    @GetMapping("/{articleId}")
    @Operation(method = "GET", summary = "단일 게시글 조회 API", description = "단일 게시글 조회하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDTO.ArticlePreviewDTO responseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(responseDto);
    }

    //게시글 수정
    @PutMapping("/{articleId}")
    @Operation(method = "PUT", summary = "게시글 수정 API", description = "게시글 수정하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(
            @PathVariable("articleId") Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleRequestDTO dto) {
        ArticleResponseDTO.ArticlePreviewDTO responseDto = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.onSuccess(responseDto);
    }

    //댓글 존재 게시글 조회
    @GetMapping("/{articleId}/hasReplies")
    @Operation(method = "GET", summary = "댓글 존재 여부 확인 API", description = "게시글에 댓글이 있는지 확인하는 API")
    public CustomResponse<Boolean> hasReplies(@PathVariable("articleId") Long articleId){
        boolean hasReplies = articleQueryService.hasReplies(articleId);
        return CustomResponse.onSuccess(hasReplies);
    }

    //커서 기반 페이지네이션
    @GetMapping("/page")
    @Operation(method = "GET", summary = "커서 기반 페이지네이션 API", description = "게시글 커서 기반 페이지네이션 API")
    public CustomResponse<ArticleResponseDTO.ArticlePagePreviewDTO> getArticlesByCursor(
            @RequestParam(value = "cursor",defaultValue = "9999999") Long cursor,
            @RequestParam(value = "size", defaultValue = "3") int size
            ){
        Slice<Article> articles = articleQueryService.getArticlesByCursor(cursor, size);
        ArticleResponseDTO.ArticlePagePreviewDTO result = ArticleConverter.from(articles);
        return CustomResponse.onSuccess(result);
    }

    //좋아요 수정
    @PatchMapping("/{articleId}")
    @Operation(method = "PATCH", summary = "게시글 좋아요 증가 API", description = "게시글 좋아요 수 1씩 증가하는 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticleLikenum(
            @PathVariable("articleId") Long articleId){
        ArticleResponseDTO.ArticlePreviewDTO responseDto = articleCommandService.updateArticleLikenum(articleId);
        return CustomResponse.onSuccess(responseDto);
    }

    //삭제
    @DeleteMapping("/{articleId}")
    @Operation(method = "DELETE", summary = "게시글 삭제 API", description = "게시글 삭제하는 API")
    public CustomResponse<String> deleteArticle(@PathVariable("articleId") Long articleId){
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(HttpStatus.NO_CONTENT,"해당 게시글이 삭제되었습니다.");
    }
}
