package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleQueryService {
    Optional<Article> getArticle(Long id);
    List<Article> getArticles();
}
