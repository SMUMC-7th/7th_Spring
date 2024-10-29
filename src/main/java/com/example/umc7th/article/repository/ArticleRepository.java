package com.example.umc7th.article.repository;

import com.example.umc7th.article.entity.Article;
import java.awt.print.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsById(Long id);

    Slice<Article> findAllById(Long id, Pageable pageable);
}
