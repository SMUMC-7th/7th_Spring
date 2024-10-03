package com.example.umc7th.article.service.query;

import com.example.umc7th.article.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleQueryService {
    Article getArticle(Long id);

    List<Article> getArticles();
}
