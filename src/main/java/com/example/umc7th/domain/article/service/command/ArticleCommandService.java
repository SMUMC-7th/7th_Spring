package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

public interface ArticleCommandService {
    ArticleResponseDTO.CreateArticleResponseDto createArticle(ArticleRequestDTO.CreateArticleRequestDTO requestDTO);
}
