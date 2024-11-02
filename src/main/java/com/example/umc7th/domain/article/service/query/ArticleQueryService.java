package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    public Slice<Article> getArticles(Long cursor, int offset, String sort);
    public List<Article> getPagedArticlesByTitle(String title, String content);
}