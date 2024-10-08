package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;

public interface ArticleCommandService {
    Article createArticle(ArticleRequestDTO.CreateArticleDTO dto);
}
