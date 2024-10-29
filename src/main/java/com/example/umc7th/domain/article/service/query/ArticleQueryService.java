package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    List<Article> getArticles(String keyword);
    boolean hasComments(Long id);
}
