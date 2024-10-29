package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
//    List<Reply> findByArticle(Article article);
    // active상태인 댓글만 조회되도록(softDelete)
    @Query("SELECT r FROM Reply r WHERE r.active = true")
    List<Reply> findAllNotDeleted();
}