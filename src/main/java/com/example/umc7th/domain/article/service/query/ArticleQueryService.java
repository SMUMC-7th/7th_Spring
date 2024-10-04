package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.dto.response.ArticleResDto;

import java.util.List;

public interface ArticleQueryService {

    List<ArticleResDto.CreateArticleResponseDto> getArticleList();
    ArticleResDto.CreateArticleResponseDto getArticle(Long articleId);
}
