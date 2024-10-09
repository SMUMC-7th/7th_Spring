package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.dto.response.ArticleResDto;

public interface ArticleQueryService {

    ArticleResDto.ArticlePreviewListDto getArticleList();
    ArticleResDto.ArticlePreviewDto getArticle(Long articleId);
}
