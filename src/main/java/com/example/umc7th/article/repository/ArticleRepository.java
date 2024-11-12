package com.example.umc7th.article.repository;

import com.example.umc7th.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsById(Long id);

    Slice<Article> findAllByIdLessThanAndTitleContainingOrderByIdDesc(Long id, Pageable pageable, String title);

    Slice<Article> findAllByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
}
