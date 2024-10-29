package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 게시글 아이디(내림차순)을 기반으로 커서 페이지네이션
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    // 좋아요수(내림차순)와 게시글 아이디(내림차순)을 조합한 값을 기반으로 커서 페이지네이션
    @Query(value = """
            SELECT a.* FROM article a
            WHERE CONCAT(LPAD(a.like_num, 10, '0'), LPAD(a.id, 10, '0')) <
                  CONCAT(LPAD(:preLikeNum, 10, '0'), LPAD(:preId, 10, '0'))
            ORDER BY a.like_num DESC, a.id DESC
            """, nativeQuery = true)
    Slice<Article> findAllByOrderByLikeNum(
            @Param("preLikeNum") int preLikeNum,
            @Param("preId") Long preId,
            Pageable pageable);

    // 제목에 특정 문자를 포함하는 게시글 페이지네이션
    @Query("SELECT a FROM Article a WHERE a.title LIKE %:title% AND a.isDeleted = false ORDER BY a.createdAt DESC")
    Page<Article> findByTitleContaining(String title, Pageable pageable);

}
