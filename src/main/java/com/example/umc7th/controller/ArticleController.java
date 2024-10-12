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
import java.util.Map;

// RestController 명시
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
@Tag(name = "게시글 API", description = "게시글 관련 API")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    @PostMapping
    @Operation(method = "POST",
            summary = "게시글 생성 API",
            description = "ArticleRequestDto형태로 게시글을 생성합니다. 만들어진 게시글id를 반환합니다.")
    public CustomResponse<Map<String, Long>> createArticle(@RequestBody ArticleRequestDto.CreateArticleRequestDto dto) {

        Long id = articleCommandService.createArticle(dto);

        Map<String, Long> result = new HashMap<>();
        result.put("articleId", id);

        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED_201, result);
    }

    @GetMapping("/{articleId}")
    @Operation(method = "GET",
            summary = "단일 게시글 조회 API",
            description = "articleId에 해당하는 게시글을 ArticleResponseDto형태로 반환합니다.")
    public CustomResponse<ArticleResponseDto.ArticlePreviewDto> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDto.ArticlePreviewDto articleResponseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, articleResponseDto);
    }

    @GetMapping
    @Operation(method = "GET",
            summary = "전체 게시글 조회 API",
            description = "전체 게시글을 ArticleResponseDto 리스트 형태로 반환합니다.")
    public CustomResponse<ArticleResponseDto.ArticlePreviewListDto> getArticles() {
        ArticleResponseDto.ArticlePreviewListDto articleResponseDtos = articleQueryService.getArticles();
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, articleResponseDtos);
    }

    @PutMapping("/{articleId}")
    @Operation(method = "PUT",
            summary = "게시글 수정 API",
            description = "articleId에 해당하는 게시글의 제목, 내용 필드를 전체 수정합니다.")
    public CustomResponse<ArticleResponseDto.ArticlePreviewDto> updateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDto.UpdateArticleRequestDto dto) {
        ArticleResponseDto.ArticlePreviewDto result = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }

    @PatchMapping("/{articleId}")
    @Operation(method = "PATCH",
            summary = "게시글 부분 수정 API",
            description = "articleId에 해당하는 게시글의 제목, 내용 필드를 부분 수정합니다.")
    public CustomResponse<ArticleResponseDto.ArticlePreviewDto> partialUpdateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDto.PartialUpdateArticleRequestDto dto) {
        ArticleResponseDto.ArticlePreviewDto result = articleCommandService.partialUpdateArticle(articleId, dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }


    @PatchMapping("/{articleId}/like")
    @Operation(method = "PATCH",
            summary = "게시글 좋아요 API",
            description = "articleId에 해당하는 게시글의 좋아요 수를 1 증가시킵니다.")
    public CustomResponse<ArticleResponseDto.ArticlePreviewDto> increaseLikeNum(@PathVariable Long articleId){
        ArticleResponseDto.ArticlePreviewDto result = articleCommandService.increaseLikeNum(articleId);
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200, result);
    }


    @DeleteMapping("/{articleId}")
    @Operation(method = "DELETE",
            summary = "게시글 삭제 API",
            description = "articleId에 해당하는 게시글을 삭제합니다.")
    public CustomResponse<String> deleteArticle(@PathVariable Long articleId){
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(GeneralSuccessCode.NO_CONTENT_204, "해당 게시글이 삭제되었습니다.");
    }
}
