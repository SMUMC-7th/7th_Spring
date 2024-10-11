package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);

    ArticleResponseDTO.ArticleUpdateDTO updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto);

    void deleteById(Long id);
}
