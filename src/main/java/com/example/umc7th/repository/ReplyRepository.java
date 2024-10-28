package com.example.umc7th.repository;

import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    boolean existsByArticle(Article article);

    @Query("SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.created_at DESC")
    Page<Reply> findAllBy(Pageable pageable, Article article);
}
