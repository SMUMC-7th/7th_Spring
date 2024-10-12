package com.example.umc7th.domain.article.repository;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}