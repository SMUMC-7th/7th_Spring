package com.example.umc7th.reply.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.umc7th.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	List<Reply> findByArticleId(Long articleId);
}
