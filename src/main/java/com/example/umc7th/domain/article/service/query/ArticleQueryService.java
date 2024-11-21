package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ArticleQueryService {
    Article getArticle(Long id);
    Slice<Article> getArticles(String query, Long cursor, Integer offset);}
