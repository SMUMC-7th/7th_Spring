package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 게시글에 댓글이 있는지 확인하는 Query
    @Query("SELECT COUNT(r) > 0 FROM Reply r WHERE r.article.id = :articleId")
    boolean existsByArticleId(@Param("articleId") Long articleId);

    // 댓글을 생성일 기준으로 내림차순 정렬하여 페이지네이션 지원하는 메소드
    Page<Reply> findAllByOrderByCreatedAtDesc(Pageable pageable);
}