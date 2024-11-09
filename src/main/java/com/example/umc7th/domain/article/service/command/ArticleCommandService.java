package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;

/**
 * Command ( 생성, 업데이트, 삭제 ) 를 위한 서비스 인터페이스
 */
public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto);
    Article increaseLike(Long id);
    void deleteArticle(Long id);
}