package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 생성 날짜 기준 커서 기반 페이지네이션
    @Query("SELECT a FROM Article a WHERE a.createdAt < :createdAt ORDER BY a.createdAt DESC")
    List<Article> findByCreatedAtLessThan(@Param("createdAt") LocalDateTime createdAt, Pageable pageable);
}
