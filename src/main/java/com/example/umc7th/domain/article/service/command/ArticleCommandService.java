package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;

public interface ArticleCommandService {

    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);

    void updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto);

    void hardDeleteArticle(Long articleId);

    void softDeleteArticle(Long articleId);

    void increaseLike(Long articleId);

}
