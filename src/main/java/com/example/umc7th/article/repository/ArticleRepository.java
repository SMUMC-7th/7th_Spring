package com.example.umc7th.article.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.umc7th.article.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어가야합니다.
// 그래서 만약 save 메서드를 호출하면, Article 객체를 반환

	// ID를 기준으로 커서 페이지네이션
	Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

	// 생성 날짜를 기준으로 커서 페이지네이션
	Slice<Article> findAllByCreatedAtBeforeOrderByCreatedAtDesc(LocalDateTime createdAt, Pageable pageable);

	// 좋아요 수를 기준으로 커서 페이지네이션
	@Query("SELECT a FROM Article a WHERE CONCAT(a.likesCount, a.id) < CONCAT(:likesCount, :id) ORDER BY a.likesCount DESC, a.id DESC")
	Slice<Article> findAllByLikesAndIdLessThanOrderByLikesCountDesc(@Param("likesCount") Integer likesCount, @Param("id") Long id, Pageable pageable);
}