package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.dto.response.ArticleResDto;

public interface ArticleQueryService {

    ArticleResDto.ArticlePreviewDto getArticle(Long articleId);
    ArticleResDto.ArticlePreviewListDto getArticlesByCursor(Long cursor, int offset, String sort);
}
