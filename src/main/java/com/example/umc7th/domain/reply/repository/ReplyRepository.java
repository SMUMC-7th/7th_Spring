package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
//    List<Reply> findByArticle(Article article);
    // active상태인 댓글만 조회되도록(softDelete)

    //해당 article을 가지고 있는 모든 답글 검색(생성날짜 내림차순 정렬)
    //JPA Query
    //List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);

    //JPQL ver.
//    @Query("SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.createdAt DESC")
//    List<Reply> findRepliesByArticleOrderByCreatedAtDescJPQL(@Param("article") Article article);

    //댓글 존재 확인 JPQL 사용
    @Query("SELECT COUNT(c) > 0 FROM Comment c WHERE c.article.id = :articleId")
    boolean existsByArticleId(@Param("articleId") Long articleId);

    //댓글 offset기반 페이지네이션(생성날짜 기준): offset은 page<> 많이 사용
    @Query("SELECT r FROM Reply r WHERE r.article.id = :articleId AND r.id < :lastId ORDER BY r.createdAt DESC")
    Page<Reply> findArticleIdByCreatedAtDesc(@Param("articleId") Long articleId,
                                               @Param("lastId") Long lastId,
                                               Pageable pageable);
}