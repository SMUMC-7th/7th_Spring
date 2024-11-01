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

    /* Repository에서 모든 답글을 찾는데, 조건으로 해당 Article을 가지고 있고 생성날짜의
   내림차순으로 정렬해서 모두 찾기 */
//    List<Reply> findAllByArticlesIsOrderByCreatedAtDesc(Article article);
    boolean existsByArticleId(Long articleId); // 특정 게시글에 댓글 여부 체크

    Page<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article, Pageable pageable); // 게시글의 댓글을 offset 기반으로
    Page<Reply> findAllByArticleIsOrderByCreatedAtDesc(Long articleId, Pageable pageable);

    List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);

    @Query("SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.createdAt DESC")
    List<Reply> findRepliesByArticleOrderByCreatedAtDescJPQL(@Param("article") Article article);

    @Query(value = "SELECT r.* FROM Reply r WHERE r.article_id = :articleId ORDER BY r.created_at DESC", nativeQuery = true)
    List<Reply> findRepliesByArticleOrderByCreatedAtDescNativeQuery(@Param("articleId") Long articleId);
}
