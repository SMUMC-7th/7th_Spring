package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어가야합니다.
// 두 번째는 id의 자료형을 적어줍니다.

    //id기준 정렬
    Slice<Article> findAllByIdLessThanOrderByIdDesc(@Param("cursor") Long cursor, Pageable pageable);
    //날짜기준 정렬,
    // 밀리 초 단위이기 때문에 그대로 정렬, 근데 이럴거면 이전 생성시간을 넘겨주는 건?

    @Query("select a from Article a where a.createdAt < :cursor " +
            "order by a.createdAt desc")
    Slice<Article> findAllByCreatedAtLessThanOrderByCreatedAtDesc(@Param("cursor")Long cursor, Pageable pageable);


    //좋아요수 기준 (jpa가 Pageable 주면 알아서 limit, offset 설정해 줌)
    // 그냥 이어붙혀서 바로 비교하려 하였으나 그럼 정렬할때 불가능함
    // id를 넘겨주기 보다 엔티티를 넘겨주면 그 순간에 좋아요가 늘어도 고정된 기준점으로 검색할 수 있는가?
    @Query(value = "SELECT a.* FROM article a " +
            "WHERE :cursor > CONCAT(LPAD(a.likeNum, 10, '0'), LPAD(a.id, 10, '0')) " +
            "ORDER BY a.likeNum DESC, a.id DESC ", nativeQuery = true)
    Slice<Article> findAllByLikeNumLessThanOrderByLikeNumDesc(@Param("cursor")Long cursor, Pageable pageable);


    //제목이 포함되는 게시글 찾기, like, 와일드 카드를 사용해서 %title%형식으로 포함된 것들 확인
    @Query("select a from Article a " +
            "where lower(a.title) like lower(concat('%', :title, '%') ) "+
            "and a.id < :cursor order by a.createdAt desc")
    Slice<Article> findByTitleContainingOrderByIdDesc(@Param("title") String title, @Param("cursor") Long cursor, Pageable pageable);
}