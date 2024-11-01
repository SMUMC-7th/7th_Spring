package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Slice<Article> findByIdLessThanOrderByIdDesc(Long cursorId, Pageable pageable);

    Slice<Article> findAllByOrderByIdDesc(Pageable pageable);

    Page<Article> findAllByOrderByCreatedAtDesc(Pageable pageable);

    Slice<Article> findAllByOrderByLikeNumDescIdDesc(Pageable pageable);
}
