package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    //첫 검색용
    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);
    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);

    //id기준 정렬
    Slice<Article> findAllByIdLessThanOrderByIdDesc(@Param("cursor") Long cursor, Pageable pageable);

    //올바른 방식의 좋아요기준 커서기반 페이지네이션
    @Query(value = "SELECT a.* FROM article a " +
            "JOIN (SELECT a2.id, CONCAT(LPAD(a2.like_num, 10, '0'), LPAD(a2.id, 10, '0')) as cursorValue FROM article a2) as cursorTable ON a.id = cursorTable.id " +
            "WHERE cursorValue < (SELECT CONCAT(LPAD(a3.like_num, 10, '0'), LPAD(a3.id, 10, '0')) as cursorValue FROM article a3 WHERE a3.id = :articleId) " +
            "ORDER BY cursorTable.cursorValue DESC",
            nativeQuery = true)
    Slice<Article> findAllByOrderByLikeWithCursor(@Param("articleId") Long cursor, Pageable pageable);

    //게시글 검색
    List<Article> findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);









    //좋아요수 기준 (jpa가 Pageable 주면 알아서 limit, offset 설정해 줌)
    // 그냥 이어붙혀서 바로 비교하려 하였으나 그럼 정렬할때 불가능함
    // id를 넘겨주기 보다 엔티티를 넘겨주면 그 순간에 좋아요가 늘어도 고정된 기준점으로 검색할 수 있는가?
    @Query(value = "SELECT a.* FROM article a " +
            "WHERE :cursor > CONCAT(LPAD(a.like_num, 10, '0'), LPAD(a.id, 10, '0')) " +
            "ORDER BY a.like_num DESC, a.id DESC ", nativeQuery = true)
    Slice<Article> findAllByLikeNumLessThanOrderByLikeNumDesc(@Param("cursor")Long cursor, Pageable pageable);


    //제목이 포함되는 게시글 찾기, like, 와일드 카드를 사용해서 %title%형식으로 포함된 것들 확인
    @Query("select a from Article a " +
            "where lower(a.title) like lower(concat('%', :title, '%') ) "+
            "and a.id < :cursor order by a.createdAt desc")
    Slice<Article> findByTitleContainingOrderByIdDesc(@Param("title") String title, @Param("cursor") Long cursor, Pageable pageable);
}