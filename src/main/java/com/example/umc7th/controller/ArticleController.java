package com.example.umc7th.controller;

import com.example.umc7th.dto.request.ArticleRequestDto;
import com.example.umc7th.dto.response.ArticleResponseDto;
import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.success.GeneralSuccessCode;
import com.example.umc7th.service.command.ArticleCommandService;
import com.example.umc7th.service.query.ArticleQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// RestController 명시
@RestController
@RequiredArgsConstructor
@Tag(name = "게시글 API", description = "게시글 관련 API")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping("/articles")
    @Operation(method = "POST",
            summary = "게시글 생성 API",
            description = "ArticleRequestDto형태로 게시글을 생성합니다. 만들어진 게시글id를 반환합니다.")
    public CustomResponse<Map<String, Long>> createArticle(@RequestBody ArticleRequestDto dto) {

        Long id = articleCommandService.createArticle(dto);

        Map<String, Long> result = new HashMap<>();
        result.put("articleId", id);

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED_201, result);
    }

    @GetMapping("/articles/{articleId}")
    @Operation(method = "GET",
            summary = "단일 게시글 조회 API",
            description = "articleId에 해당하는 게시글을 ArticleResponseDto형태로 반환합니다.")
    public CustomResponse<ArticleResponseDto> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDto articleResponseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, articleResponseDto);
    }

    @GetMapping("/articles")
    @Operation(method = "GET",
            summary = "전체 게시글 조회 API",
            description = "전체 게시글을 ArticleResponseDto 리스트 형태로 반환합니다.")
    public CustomResponse<List<ArticleResponseDto>> getArticles() {
        List<ArticleResponseDto> articleResponseDtos = articleQueryService.getArticles();
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, articleResponseDtos);
    }
}
