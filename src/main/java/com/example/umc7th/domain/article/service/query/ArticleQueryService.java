package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleQueryService {
    List<ArticleResponseDTO.ArticlePreviewDTO> getArticleList();
    ArticleResponseDTO.ArticlePreviewDTO getArticle(Long articleId);
    boolean hasReplies(Long articleId);
    Slice<Article> getArticlesByCursor(Long cursor, int size);
}
