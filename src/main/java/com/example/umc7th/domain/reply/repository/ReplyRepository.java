package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    boolean existsByArticleId(Long articleId);
    Page<Reply> findAllByArticleIdOrderByCreatedAtDesc(Long articleId, Pageable pageable);
}
