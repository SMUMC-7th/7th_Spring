package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// 서버가 클라이언트로 데이터 반환할 때 사용하는 DTO
// 서버에서는 데이터를 가공해서 클라이언트에게 필요로 하는 정보만 보내줌
public class ArticleResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreateArticleResponseDTO {
        private Long id;
        private LocalDateTime createdAt;

        /**
         * Article 엔티티를 CreateArticleResponseDTO로 변환하는 메서드
         * @param article (변환할 Article 엔티티 객체)
         * @return CreateAticleResponseDTO 객체
         */
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

        /**
         * Article 엔티티를 ArticlePreviewDTO로 변환하는 메서드
         *
         * @param optionalArticle (변환활 Article 엔티티 객체)
         * @return ArticlePreviewDTO 객체
         */
        public static Optional<ArticlePreviewDTO> from(Optional<Article> optionalArticle) {
            return optionalArticle.map(article -> ArticlePreviewDTO.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .createdAt(article.getCreatedAt())
                    .updatedAt(article.getUpdatedAt())
                    .build());
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    // 게시글 목록 조회할 때 사용
    public static class ArticlePreviewListDTO {
        private List<ArticlePreviewDTO> articles;

        /**
         * 게시글 리스트를 ArticlePreviewDTO 리스트로 변환한 후 ArticlePreviewListDTO로 감싸는 메서드
         * @param articles (변환할 Article 엔티티 리스트)
         * @return ArticlePreviewListDTO 객체
         */
            public static ArticlePreviewListDTO from(List<Article> articles) {
                return ArticlePreviewListDTO.builder()
                        .articles(articles.stream()
                                .map(article -> ArticlePreviewDTO.from(Optional.of(article)).get())
                                .toList())
                        .build();
            }
    }
}
