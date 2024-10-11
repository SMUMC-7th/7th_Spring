package com.example.umc7th.article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.umc7th.article.entity.Article;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ArticleResponseDTO {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreateArticleResponseDTO {
        private Long id;
        private LocalDateTime createdAt;

        public static CreateArticleResponseDTO from(Article article) {
            return CreateArticleResponseDTO.builder()
                .id(article.getId())
                .createdAt(article.getCreatedAt())
                .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewDTO {
        private Long id;
        private String title;
        private String content;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public static ArticlePreviewDTO from(Article article) {
            return ArticlePreviewDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class ArticlePreviewListDTO {
        private List<ArticlePreviewDTO> articles;
        public static ArticlePreviewListDTO from(List<Article> articles) {
            return ArticlePreviewListDTO.builder()
                .articles(articles.stream().map(ArticlePreviewDTO::from).toList())
                .build();
        }
    }
}
