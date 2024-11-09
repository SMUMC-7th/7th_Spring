package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 특정 게시글에 속한 모든 댓글을 생성일 역순으로 조회
    List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);

    // 특정 게시글에 속한 모든 댓글을 페이지 단위로 생성일 역순으로 조회
    // 단순한 조회나 가벼운 조건 조회시 사용
    Page<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article, Pageable pageable);

    // JPQL을 사용하여 특정 게시글의 댓글을 생성일 역순으로 조회
    // 상대적으로 복잡한 조건 시 사용
    @Query("SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.createdAt DESC")
    List<Reply> findRepliesByArticleOrderByCreatedAtDescJPQL(@Param("article") Article article);

    // 네이티브 쿼리를 사용하여 특정 게시글의 댓글을 생성일 역순으로 조회
    // 고성능이 필요하거나 DBMS에 특화된 기능을 써야 한다면 사용
    @Query(value = "SELECT r.* FROM reply r WHERE r.article_id = :articleId ORDER BY r.created_at DESC", nativeQuery = true)
    List<Reply> findRepliesByArticleOrderByCreatedAtDescNativeQuery(@Param("articleId") Long articleId);

    // 특정 게시글에 댓글이 존재하는지 확인하는 메서드
    boolean existsByArticleIs(Article article);

}