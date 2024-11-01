package com.example.umc7th.article.service.query;

import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    boolean hasReplies(Long articleId);
    Slice<Article> getArticlesByCursor(LocalDateTime cursor, int size);
}
