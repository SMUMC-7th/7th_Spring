package com.example.umc7th.article.repository;

import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findArticleById(Long id);
}
