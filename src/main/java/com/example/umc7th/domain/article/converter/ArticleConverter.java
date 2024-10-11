package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

import java.util.List;

public class ArticleConverter {

    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likeNum(0)
                .build();
    }

    public static ArticleResponseDTO.CreateArticleResponseDTO toCreateArticleResponseDTO(Article article) {
        return ArticleResponseDTO.CreateArticleResponseDTO.builder()
                .id(article.getId())
                .createdAt(article.getCreatedAt())
                .build();
    }

    public static ArticleResponseDTO.DeletedArticleDTO toDeletedArticleDTO(Long id) {
        return ArticleResponseDTO.DeletedArticleDTO.builder()
                .id(id)
                .build();
    }

    public static ArticleResponseDTO.ArticlePreviewDTO toArticlePreviewDTO(Article article) {
        return ArticleResponseDTO.ArticlePreviewDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    public static ArticleResponseDTO.ArticlePreviewListDTO toArticlePreviewListDTO(List<Article> articles) {
        return ArticleResponseDTO.ArticlePreviewListDTO.builder()
                .articles(articles.stream().map(ArticleConverter::toArticlePreviewDTO).toList())
                .build();

    }
}
