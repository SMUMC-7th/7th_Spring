package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleConverter {

    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO createArticleDTO) {

        return Article.builder()
                .title(createArticleDTO.getTitle())
                .content(createArticleDTO.getContent())
                .likeNum(0)
                .build();
    }

    public static ArticleResponseDTO.CreateArticleResultDTO toCreateArticleResultDTO(Article article) {

        return ArticleResponseDTO.CreateArticleResultDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .build();
    }

    public static ArticleResponseDTO.ArticleViewDTO toArticleViewDTO(Article article) {

        return ArticleResponseDTO.ArticleViewDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    public static List<ArticleResponseDTO.ArticleViewDTO> toArticleViewListDTO(List<Article> articles) {

        return articles.stream()
                .map(ArticleConverter::toArticleViewDTO)
                .collect(Collectors.toList());
    }
}
