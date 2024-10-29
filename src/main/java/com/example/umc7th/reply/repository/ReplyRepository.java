package com.example.umc7th.reply.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	// 댓글이 있는지 확인
	boolean existsByArticleId(Long articleId);

	// 오프셋 기반 페이지네이션 (native query)
	@Query(value = "SELECT r.* FROM reply r WHERE r.article_id = :articleId ORDER BY r.created_at DESC", nativeQuery = true)
	List<Reply> findRepliesByArticleOrderByCreatedAtDescNativeQuery(@Param("articleId") Long articleId);

	// 댓글 커서 기반 페이지네이션: ID를 기준으로
	@Query("SELECT r FROM Reply r WHERE r.article.id = :articleId AND r.id < :lastId ORDER BY r.id DESC")
	List<Reply> findCursorBasedReplies(@Param("articleId") Long articleId, @Param("lastId") Long lastId, Pageable pageable);
}
