package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 게시글 조회를 위한 query 서비스 인터페이스
 */
public interface ArticleQueryService {
    Optional<Article> getArticle(Long id);
    List<Article> getArticles();
    List<Article> getArticlesByCreatedAtLessThan(LocalDateTime createdAt, Pageable pageable);
}
