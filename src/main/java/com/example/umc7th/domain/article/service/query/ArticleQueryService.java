package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    List<Article> getArticles(String keyword);
    boolean hasComments(Long id);

    Slice<Article> getArticlesOrderById(Long id, int size);
    Slice<Article> getArticlesOrderByCreatedAt(LocalDateTime cursor, int size);
    Slice<Article> getArticlesOrderByLikeNum(String cursor, int size);
}
