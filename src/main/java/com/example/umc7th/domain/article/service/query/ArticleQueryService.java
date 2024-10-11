package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.dto.ArticleResponseDTO;

import java.util.List;

public interface ArticleQueryService {
    ArticleResponseDTO getArticle(Long id);
    List<ArticleResponseDTO> getArticles();
}
