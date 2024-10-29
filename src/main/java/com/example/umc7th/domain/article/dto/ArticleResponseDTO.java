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

    @Getter
    @Builder
    // 페이지네이션 응답
    public static class ArticleSliceResponse {
        private List<ArticleViewDTO> articleViewDTOs;
        private int currentPage; // 현재 페이지
        private boolean hasNext; // 다음 페이지 존재 여부
        private int pageSize; // 한 페이지 사이즈
        private long numberOfElements; // 전체 객체 수
    }

}
