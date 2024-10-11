package com.example.umc7th.domain.article.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {

    @Getter
    @Builder
    // Article 생성의 응답
    public static class CreateArticleResultDTO {
        private Long id;
        private String title;
        private String content;
        private int likeNum;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    // Article 검색의 응답
    public static class ArticleViewDTO {
        private Long id;
        private String title;
        private String content;
        private int likeNum;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
    
    @Getter
    @Builder
    // 여러 Article 검색의 응답
    public static class ArticleViewListDTO {
        private List<ArticleViewDTO> articleViewDTOs;
    }

}
