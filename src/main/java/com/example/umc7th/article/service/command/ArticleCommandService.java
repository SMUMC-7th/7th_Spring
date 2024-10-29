package com.example.umc7th.article.service.command;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.entity.Article;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto);
    // 게시물 삭제 로직 추가
    void deleteArticle(Long id);
}