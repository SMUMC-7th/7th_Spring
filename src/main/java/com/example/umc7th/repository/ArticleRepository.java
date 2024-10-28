package com.example.umc7th.repository;

import com.example.umc7th.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByIdAndActiveTrue(Long id);

    List<Article> findByActiveTrue();



    @Query("SELECT a FROM Article a " +
            "WHERE (a.likeNum < :likeCount) " +
            "OR (a.likeNum = :likeCount AND a.id < :articleId) " +
            "ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findArticlesWithCursor(
            @Param("likeCount") int likeCount,
            @Param("articleId") Long articleId,
            Pageable pageable
    );

    @Query("SELECT a FROM Article a " +
            "ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findFirstPage(Pageable pageable);

}
