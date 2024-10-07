package com.example.umc7th.article.converter;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;

import java.util.List;

public class ArticleConverter {

    public static Article toEntity(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likeNum(0)
                .build();
    }

    public static ArticleResponseDTO toArticleResponseDto(Article article) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    public static List<ArticleResponseDTO> toArticleResponseDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(ArticleConverter::toArticleResponseDto)
                .toList();
    }
}
