package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreateArticleResponseDTO { //게시물 생성 DTO
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
    public static class ArticlePreviewDTO { //게시물 단일 조회 DTO
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
    public static class ArticlePreviewListDTO { //게시물 전체 조회 DTO
        private List<ArticlePreviewDTO> articles;
        public static ArticlePreviewListDTO from(List<Article> articles) {
            return ArticlePreviewListDTO.builder()
                    .articles(articles.stream().map(ArticlePreviewDTO::from).toList())
                    .build();
        }
    }
}
