package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.stream;


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

        public static ArticlePreviewListDTO from(List<Article> artiles) {
            return ArticlePreviewListDTO.builder()
                    .articles(artiles.stream().map(ArticlePreviewDTO::from).toList())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class UpdateArticleResponseDTO {
        private Long id;
        private LocalDateTime updatedAt;

        public static UpdateArticleResponseDTO from(Article article) {
            return UpdateArticleResponseDTO.builder()
                    .id(article.getId())
                    .updatedAt(article.getUpdatedAt())
                    .build();
        }

    }
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class DeleteArticleResponseDTO {
        private Long id;

        public static DeleteArticleResponseDTO from(Article article) {
            return DeleteArticleResponseDTO.builder()
                    .id(article.getId())
                    .build();
        }
    }

}
