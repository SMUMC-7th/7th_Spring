package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    public Slice<Article> getPagedArticles(Long cursor, int offset, String sort);
    public Slice<Article> getPagedArticlesByTitle(String title, Long cursor, int offset);
}