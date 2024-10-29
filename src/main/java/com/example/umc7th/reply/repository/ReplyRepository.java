package com.example.umc7th.reply.repository;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.entity.Reply;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByArticleId(Long articleId);

    boolean existsByArticleId(Long articleId);

    @Query("SELECT r from Reply r WHERE r.article =:article order by r.createdAt DESC ")
    Page<Reply> findAllByArticleIdIsOrderByCreatedAtDesc(@Param("article") Article article, Pageable pageable);
}
