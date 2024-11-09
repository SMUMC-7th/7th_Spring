package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// JpaRepository 상속, Article 엔티티와 ID 타입을 정의하여 기본적인 CRUD 메서드 사용 가능
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 특정 ID보다 작은 ID를 가진 게시글을 ID 역순으로 조회하여, 페이징된 결과로 반환
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    // 모든 게시글을 ID 역순으로 조회하여, 페이징된 결과로 반환
    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

    // 커서 기반으로 좋아요 수와 ID를 조합하여 역순으로 정렬된 게시글을 조회하는 쿼리
    @Query(value = "SELECT a.* FROM article a " +
            "JOIN (SELECT a2.id, CONCAT(LPAD(a2.like_num, 10, '0'), LPAD(a2.id, 10, '0')) as cursorValue FROM article a2) as cursorTable ON a.id = cursorTable.id " +
            "WHERE cursorValue < (SELECT CONCAT(LPAD(a3.like_num, 10, '0'), LPAD(a3.id, 10, '0')) as cursorValue FROM article a3 WHERE a3.id = :articleId) " +
            "ORDER BY cursorTable.cursorValue DESC",
            nativeQuery = true)
    Slice<Article> findAllByOrderByLikeWithCursor(@Param("articleId") Long cursor, Pageable pageable);

    // 좋아요 수 내림차순, ID 내림차순으로 모든 게시글을 조회하여 페이징된 결과로 반환
    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);

    // 제목 또는 내용에 특정 키워드를 포함하는 게시글을 대소문자 구분 없이 조회
    List<Article> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);

}
