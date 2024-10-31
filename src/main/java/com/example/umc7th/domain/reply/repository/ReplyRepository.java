package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 특정 게시글에 댓글이 존재하는지 확인하는 메서드
    boolean existsByArticleId(Long articleId);
    List<Reply> findAllByArticle(Article article);
    Page<Reply> findAllByArticleOrderByCreatedAtDesc(Article article, Pageable pageable);
}
