package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //댓글 존재 확인 JPQL 사용
    @Query("SELECT COUNT(r) > 0 FROM Reply r WHERE r.article.id = :articleId")
    boolean existsByArticleId(@Param("articleId") Long articleId);

    //cursor기반 페이지네이션
    //id기준
    Slice<Article> findAllByIdGreaterThan(Long cursorId, Pageable pageable);
}