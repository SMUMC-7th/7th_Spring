package com.example.umc7th.domain.article.service.query;

import org.springframework.data.domain.Slice;

import com.example.umc7th.domain.article.entity.Article;

public interface ArticleQueryService {
    Article getArticle(Long id);
    Slice<Article> getArticles(String query, Long cursor, Integer offset);
}
