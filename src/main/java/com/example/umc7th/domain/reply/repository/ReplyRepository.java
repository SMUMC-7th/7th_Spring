package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByArticleAndActiveTrue(Article article);
    boolean existsByArticleId(Long articleId);
    //모든 답글 찾음(생성날짜 내림차순 정렬)
    //@Query(value = "SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.createdAt DESC", nativeQuery = true)
    Page<Reply> findAllByArticleIdOrderByCreatedAtDesc(Long articleId, Pageable pageable);
}
