package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

import java.util.List;

public interface ArticleQueryService {
    List<ArticleResponseDTO.CreateArticleResponseDto> getArticleList();
    ArticleResponseDTO.CreateArticleResponseDto getArticle(Long articleId);
}
