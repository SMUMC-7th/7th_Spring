package com.example.umc7th.domain.article.controller;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.service.command.ArticleCommandService;
import com.example.umc7th.domain.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// RestController 명시
@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입).
@RequiredArgsConstructor
@Tag(name = "게시물 API")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping("/articles")
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    @Operation(method = "POST", summary = "게시글 작성 API")
    public CustomResponse<ArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // CustomResponse에 article을 담아 성공했다고 응답하기
        return CustomResponse.onSuccess(ArticleResponseDTO.toArticleResponseDTO(article));
    }

    // 생성이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
    @GetMapping("/articles/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    @Operation(method = "POST", summary = "단일 게시글 검색 API")
    public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
        // 구현
        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.toArticleResponseDTO(article));
    }

    @GetMapping("/articles")
    @Operation(method = "POST", summary = "모든 게시글 검색 API")
    public CustomResponse<List<ArticleResponseDTO>> getArticles() {
        // 구현
        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(articles.stream().map(ArticleResponseDTO::toArticleResponseDTO).toList());
    }
}