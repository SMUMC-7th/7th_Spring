package com.example.umc7th.article.repository;

import com.example.umc7th.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository의 첫 번째는 해당 Repository가 사용할 클래스(엔티티)가 들어감
// 두 번째는 id의 자료형을 적어줌
public interface ArticleRepository extends JpaRepository<Article, Long> {
}