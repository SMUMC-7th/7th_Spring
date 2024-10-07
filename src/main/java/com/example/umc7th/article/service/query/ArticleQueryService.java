package com.example.umc7th.article.service.query;

import com.example.umc7th.article.dto.ArticleResponseDTO;

import java.util.List;

public interface ArticleQueryService {
    ArticleResponseDTO getArticle(Long id);
    List<ArticleResponseDTO> getArticles();
}
