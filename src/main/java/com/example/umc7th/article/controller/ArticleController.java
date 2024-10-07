package com.example.umc7th.article.controller;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// 생성자 의존성 주입을 위한 Annotation (private final로 정의된 필드에 의존성 주입)
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    // 생성이므로 POST method 사용
    @PostMapping("/articles")
    // 요청 시 데이터를 담을 DTO 를 설정해주고 RequestBody 라는 것을 명시
    public CustomResponse<Article> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        // service에서 게시글 생성한 게시글 가져오기
        Article article = articleCommandService.createArticle(dto);
        // CustomResponse에 article을 담아 성공했다고 응답하기
        return CustomResponse.onSuccess(article);
    }

    // 생성이므로 GET method 사용
    // 뒤에 ID 값을 놓도록 설정
    @GetMapping("/articles/{articleId}")
    // @PathVariable을 이용하여 {}로 설정한 변수의 값을 가져온 이후 Long articleId에 담기
    // GET method는 RequestBody 사용이 불가능
    public CustomResponse<ArticleResponseDTO> getArticle(@PathVariable("articleId") Long articleId) {
        ArticleResponseDTO article = articleQueryService.getArticle(articleId);
        return CustomResponse.onSuccess(article);
    }

    @GetMapping("/articles")
    public CustomResponse<List<ArticleResponseDTO>> getArticles() {
        List<ArticleResponseDTO> articles = articleQueryService.getArticles();
        return CustomResponse.onSuccess(articles);
    }

}
