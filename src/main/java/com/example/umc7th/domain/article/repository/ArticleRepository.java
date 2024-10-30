package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // id 기준 커서 페이지네이션
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    // 생성 날짜 기준 커서 페이지네이션
    Slice<Article> findAllByCreatedAtLessThanOrderByCreatedAtDesc(LocalDateTime createdAt, Pageable pageable);

    // 좋아요 수 기준 커서 페이지네이션 (좋아요 수와 id를 결합한 커서 사용)
    @Query("SELECT a FROM Article a WHERE CONCAT(a.likeNum, a.id) < CONCAT(:likeNum, :id) ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findByLikeNumAndIdCursor(@Param("likeNum") int likeNum, @Param("id") Long id, Pageable pageable);

    // 제목에 특정 키워드를 포함하는 게시글 조회
    List<Article> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}
