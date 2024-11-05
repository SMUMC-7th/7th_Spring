package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어감
// 두 번째는 id의 자료형을 적어줌
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long cursor, Pageable pageable);
    Slice<Article> findAllByCreatedAtLessThanOrderByCreatedAtDesc(LocalDateTime cursor, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword%")
    List<Article> findByTitleContaining(String keyword);

    @Query(value =
            "SELECT * FROM Article a " +
            "WHERE CONCAT(LPAD(a.like_num, 10, '0'), LPAD(a.id, 10, '0')) < :cursor " +
            "ORDER BY a.like_num DESC, a.id DESC",
            nativeQuery = true)
    Slice<Article> findByLikeNumCursor(@Param("cursor") String cursor, Pageable pageable);

}