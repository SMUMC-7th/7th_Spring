package com.example.umc7th.domain.reply.repository;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByArticle(Article article);

    // 해당 메소드가 데이터베이스의 상태를 변경함을 명시
    @Modifying
    void deleteByArticleId(@Param("articleId") Long articleId);

}
