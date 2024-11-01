package com.example.umc7th.article.repository;

import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Slice<Article> findAllByCreatedAtLessThanOrderByCreatedAtDesc(LocalDateTime cursor, Pageable pageable);
}
