package com.example.umc7th.article.service.query;

import java.util.List;

import com.example.umc7th.article.entity.Article;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
}