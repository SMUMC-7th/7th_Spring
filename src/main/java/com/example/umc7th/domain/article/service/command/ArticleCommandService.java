package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

public interface ArticleCommandService {
    ArticleResponseDTO.ArticlePreviewDTO createArticle(ArticleRequestDTO.CreateArticleRequestDTO requestDTO);
    ArticleResponseDTO.ArticlePreviewDTO updateArticle(Long articleId,ArticleRequestDTO.UpdateArticleRequestDTO requestDTO);
    ArticleResponseDTO.ArticlePreviewDTO updateArticleLikenum(Long articleId);
    void deleteArticle(Long articleId);
}
