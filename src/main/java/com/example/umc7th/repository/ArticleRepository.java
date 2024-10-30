package com.example.umc7th.repository;

import com.example.umc7th.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByIdAndActiveTrue(Long id);

    List<Article> findByActiveTrue();



    @Query("SELECT a FROM Article a " +
            "WHERE (a.likeNum < :likeCount) " +
            "OR (a.likeNum = :likeCount AND a.id < :articleId) " +
            "ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findArticlesWithCursor(
            @Param("likeCount") int likeCount,
            @Param("articleId") Long articleId,
            Pageable pageable
    );
/*   CONCAT + LPAD 버전
    @Query(value = "SELECT a.* FROM article a " +
            "JOIN (SELECT a2.id, CONCAT(LPAD(a2.like_num, 10, '0'), LPAD(a2.id, 10, '0')) as cursorValue FROM article a2) as cursorTable ON a.id = cursorTable.id " +
            "WHERE cursorValue < (SELECT CONCAT(LPAD(a3.like_num, 10, '0'), LPAD(a3.id, 10, '0')) as cursorValue FROM article a3 WHERE a3.id = :articleId) " +
            "ORDER BY cursorTable.cursorValue DESC",
            nativeQuery = true)
    Slice<Article> findAllByOrderByLikeWithCursor(@Param("articleId") Long cursor, Pageable pageable);
*/
    @Query("SELECT a FROM Article a " +
            "ORDER BY a.likeNum DESC, a.id DESC")
    Slice<Article> findFirstPage(Pageable pageable);

}
