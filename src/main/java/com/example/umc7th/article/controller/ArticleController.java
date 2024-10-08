package com.example.umc7th.article.controller;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.dto.DetailArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.service.command.ArticleCommandService;
import com.example.umc7th.article.service.query.ArticleQueryService;
import com.example.umc7th.controller.global.apiPayload.CustomResponse;
import com.example.umc7th.controller.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleQueryService articleQueryService;
    private final ArticleCommandService articleCommandService;

    //Create, Post 요청 사용
    @PostMapping("/articles")
    public CustomResponse<?> createArticle(@RequestBody ArticleRequestDTO.CreateArticleDTO dto) {
        //요청 시 데이터를 담을 DTO를 설정해주고, RequestBody라는 것을 명시
        Article article = articleCommandService.createArticle(dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.CREATED, article.getId());
    }

    // 조회이므로 Get 메소드 사용한다.
    @GetMapping("/articles/{articleId}")
    public CustomResponse<?> getDetailArticle(@PathVariable("articleId") Long articleId) {
        DetailArticleResponseDTO articleDto = new DetailArticleResponseDTO(articleQueryService.getDetailArticle(articleId));
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, articleDto);
    }

    //여러개 조회
    @GetMapping("/articles")
    public CustomResponse<?> getAllArticle() {
        ArticleResponseDTO articles = new ArticleResponseDTO(articleQueryService.getArticles());
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, articles);
    }
}
