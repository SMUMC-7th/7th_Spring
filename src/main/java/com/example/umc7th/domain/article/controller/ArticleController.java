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
import java.util.Map;

// RestController 명시
@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입).
@RequiredArgsConstructor
@Tag(name = "게시물 API")
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping
    // 요청 시 데이터를 담을 DTO를 설정해주고 RequestBody라는 것을 명시
    @Operation(method = "POST", summary = "게시글 작성 API")
    public CustomResponse<ArticleResponseDTO.CreateArticleResponseDTO> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {

        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.CreateArticleResponseDTO.from(article));
    }

    // 생성이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정 ex) /article/1로 요청이 오면 1을 변수로 사용
    @GetMapping("/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기. 참고로 GET method는 RequestBody 사용이 불가능합니다.
    @Operation(method = "POST", summary = "단일 게시글 검색 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> getArticle(@PathVariable("articleId") Long articleId) {

        Article article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @GetMapping
    @Operation(method = "POST", summary = "모든 게시글 검색 API")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewListDTO> getArticles() {

        List<Article> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewListDTO.from(articles));
    }

    @PostMapping("/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(@PathVariable Long articleId, @RequestBody ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleCommandService.updateArticle(articleId, dto);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    //patchMapping 구현
    @PatchMapping("/{articleId}")
    public CustomResponse<ArticleResponseDTO.ArticlePreviewDTO> updateArticle(@PathVariable Long articleId, Map<String,Object> updates){
        Article article = articleCommandService.patchArticle(articleId, updates);
        return CustomResponse.onSuccess(ArticleResponseDTO.ArticlePreviewDTO.from(article));
    }

    @DeleteMapping("/{articleId}")
    public CustomResponse<Long> deleteArticle(@PathVariable Long articleId) {
        Long id = articleCommandService.deleteArticle(articleId);
        return CustomResponse.onSuccess(id);
    }
}