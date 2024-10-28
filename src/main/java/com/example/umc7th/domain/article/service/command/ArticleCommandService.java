package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;

import java.util.Map;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
    public Article updateArticle(Long Id, ArticleRequestDTO.UpdateArticleDTO dto);
    public Long deleteArticle(Long id);
    //patch용, 안쓰면 삭제
    public Article patchArticle(Long Id, Map<String,Object> updates);
}