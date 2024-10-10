package com.example.umc7th.article.controller;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.dto.DetailArticleResponseDTO;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Article 관련 API")
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    //Create, Post 요청 사용
    @PostMapping("/articles")
    @Operation(summary = "게시글 생성 API", description = "새 게시글을 생성하는 API이고, 생성된 Article의 id를 반환합니다.")
    public CustomResponse<?> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        //요청 시 데이터를 담을 DTO를 설정해주고, RequestBody라는 것을 명시
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, articleCommandService.createArticle(dto).getId());
    }

    // 조회이므로 Get 메소드 사용한다.
    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 한 개 조회하는 API ", description = "게시글 한 개를 조회하는 API 입니다.")
    public CustomResponse<?> getDetailArticle(@PathVariable("articleId") Long articleId) {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, DetailArticleResponseDTO.from(articleQueryService.getDetailArticle(articleId)));
    }

    //여러개 조회
    @GetMapping("/articles")
    @Operation(summary = "게시글 여러개 조회 API", description = "게시글 전체를 조회하는 API 입니다. ")
    public CustomResponse<?> getAllArticle() {
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, ArticleResponseDTO.from(articleQueryService.getArticles()));
    }

    @PutMapping("/articles/{articleId}")
    public CustomResponse<?> updateArticle(@PathVariable Long articleId, @RequestBody ArticleRequestDTO.UpdateReplyDTO dto) {
        articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }

    @DeleteMapping("/articles/{articleId}")
    public CustomResponse<?> deleteArticle(@PathVariable Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }
}
