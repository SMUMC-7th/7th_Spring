package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;

import java.util.List;

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

    public static ArticleResponseDTO.ArticleViewListDTO toArticleViewListDTO(List<Article> articles) {

        List<ArticleResponseDTO.ArticleViewDTO> articleViewDTOs = articles.stream()
                .map(ArticleConverter::toArticleViewDTO)
                .toList();

        return ArticleResponseDTO.ArticleViewListDTO.builder()
                .articleViewDTOs(articleViewDTOs)
                .build();
    }
}
