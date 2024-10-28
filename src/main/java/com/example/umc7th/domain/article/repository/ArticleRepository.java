package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    Optional<Article> findByIdAndActiveTrue(Long id);
    List<Article> findByActiveTrue();
    // id를 기준으로 커서 페이지네이션
    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long cursor, Pageable pageable);
}
