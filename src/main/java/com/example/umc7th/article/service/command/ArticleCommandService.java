package com.example.umc7th.article.service.command;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);

    void updateArticle(Long articleId, ArticleRequestDTO.UpdateReplyDTO dto);

    void deleteArticle(Long articleId);

    void addLikeNum(Long articleId);
}
