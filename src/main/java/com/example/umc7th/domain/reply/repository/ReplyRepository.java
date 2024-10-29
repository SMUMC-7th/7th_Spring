package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
//    List<Reply> findByArticle(Article article);
    // active상태인 댓글만 조회되도록(softDelete)

    //Method 해당 article을 가지고 있는 모든 답글 검색(생성날짜 내림차순 정렬)
    //JPA Query
    //List<Reply> findAllByArticleIsOrderByCreatedAtDesc(Article article);

    //JPQL ver.
    @Query("SELECT r FROM Reply r WHERE r.article = :article ORDER BY r.createdAt DESC")
    List<Reply> findRepliesByArticleOrderByCreatedAtDescJPQL(@Param("article") Article article);
}