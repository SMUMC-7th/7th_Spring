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
    public CustomResponse<ArticleResDto.ArticlePreviewListDto> getArticleList() {
        ArticleResDto.ArticlePreviewListDto articles = articleQueryService.getArticleList();
        return CustomResponse.onSuccess(articles);
    }

    @Operation(summary = "게시글 단일 조회", description = "게시글의 ID로 게시글을 단일 조회합니다.")
    @GetMapping("/{articleId}")
    public CustomResponse<ArticleResDto.ArticlePreviewDto> getArticle(@PathVariable Long articleId) {
        ArticleResDto.ArticlePreviewDto responseDto = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(responseDto);
    }

    @PutMapping("/{articleId}")
    public CustomResponse<String> updateArticle(@PathVariable Long articleId,
                                                @RequestBody ArticleReqDto.UpdateArticleRequestDto requestDto) {
        articleCommandService.updateArticle(articleId, requestDto);
        return CustomResponse.onSuccess("게시글 수정이 완료되었습니다.");
    }

    @PatchMapping("/{articleId}/title")
    public CustomResponse<String> updateArticleTitle(@PathVariable Long articleId,
                                                     @RequestBody ArticleReqDto.UpdateArticleTitleRequestDto requestDto) {
        articleCommandService.updateArticleTitle(articleId, requestDto);
        return CustomResponse.onSuccess("게시글의 제목 수정이 완료되었습니다.");
    }

    @PatchMapping("/{articleId}/content")
    public CustomResponse<String> updateArticleContent(@PathVariable Long articleId,
                                                       @RequestBody ArticleReqDto.UpdateArticleContentRequestDto requestDto) {
        articleCommandService.updateArticleContent(articleId, requestDto);
        return CustomResponse.onSuccess("게시글의 내용 수정이 완료되었습니다.");
    }

    @PatchMapping("/{articleId}")
    public CustomResponse<String> deleteArticle(@PathVariable Long articleId) {
        articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess("게시글 삭제가 완료되었습니다.");
    }
}
