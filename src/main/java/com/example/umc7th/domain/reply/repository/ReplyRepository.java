package com.example.umc7th.domain.reply.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);
    Page<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article, Pageable pageable);

    @Query("SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.createdAt DESC")
    List<Reply> findRepliesByArticleOrderByCreatedAtDescJPQL(@Param("article") Article article);

    @Query(value = "SELECT r.* FROM reply r WHERE r.article_id = :articleId ORDER BY r.created_at DESC", nativeQuery = true)
    List<Reply> findRepliesByArticleOrderByCreatedAtDescNativeQuery(@Param("articleId") Long articleId);

    boolean existsByArticleIs(Article article);

}
