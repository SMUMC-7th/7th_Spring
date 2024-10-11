package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController 명시
@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입)
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping("/articles")
    @Operation(summary = "게시글 생성")
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    public CustomResponse<ArticleResponseDTO.CreateArticleResultDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // CustomResponse에 article을 담아 성공했다고 응답하기
        return CustomResponse.onSuccess(ArticleConverter.toCreateArticleResultDTO(article));
    }

    @PutMapping("/articles/{articleId}")
    @Operation(summary = "게시글 수정")
    public CustomResponse<String> updateArticle(
            @PathVariable Long articleId,
            @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {

        articleCommandService.updateArticle(articleId, dto);

        return CustomResponse.onSuccess("게시글 수정에 성공하였습니다.");
    }

    @DeleteMapping("/articles/{articleId}")
    @Operation(summary = "게시글 hard 삭제")
    public CustomResponse<String> deleteArticle(@PathVariable Long articleId) {

        articleCommandService.hardDeleteArticle(articleId);

        return CustomResponse.onSuccess("게시글 영구 삭제에 성공하였습니다.");
    }

    @DeleteMapping("/articles/{articleId}/soft-delete")
    @Operation(summary = "게시글 soft 삭제")
    public CustomResponse<String> softDeleteArticle(@PathVariable Long articleId) {

        articleCommandService.softDeleteArticle(articleId);

        return CustomResponse.onSuccess("게시글 soft 삭제에 성공하였습니다.");
    }

    @PatchMapping("/articles/{articleId}/like")
    @Operation(summary = "게시글 좋아요 수 증가")
    public CustomResponse<String> increaseLike(@PathVariable Long articleId) {

        articleCommandService.increaseLike(articleId);

        return CustomResponse.onSuccess("게시글 좋아요 수 증가에 성공하였습니다.");
    }

    @GetMapping("/articles/{articleId}")
    @Operation(summary = "게시글 하나 조회")
    public CustomResponse<ArticleResponseDTO.ArticleViewDTO> getArticle(@PathVariable("articleId") Long articleId) {

        Article article = articleQueryService.getArticle(articleId);

        return CustomResponse.onSuccess(ArticleConverter.toArticleViewDTO(article));
    }

    @GetMapping("/articles")
    @Operation(summary = "게시글 전체 조회")
    public CustomResponse<ArticleResponseDTO.ArticleViewListDTO> getArticles() {

        List<Article> articles = articleQueryService.getArticles();

        return CustomResponse.onSuccess(ArticleConverter.toArticleViewListDTO(articles));
    }
}
