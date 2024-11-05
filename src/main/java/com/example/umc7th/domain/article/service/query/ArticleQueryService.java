package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.controller.ArticleSearchCond;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    List<Article> getArticles(String keyword);
    boolean hasComments(Long id);

    ArticleResponseDTO.ArticleCursorPreviewListDTO getArticlesOrderBy(ArticleSearchCond sortCond, String cursor, int size);
}
