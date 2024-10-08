package com.example.umc7th.reply.repository;

import com.example.umc7th.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByArticleId(Long articleId);
}
