package com.example.umc7th.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


public class ArticleResponseDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ArticlePreviewDto {
        public Long id;
        public String title;
        public String content;
        public int likeNum;
        public LocalDateTime createdAt;
        public LocalDateTime updatedAt;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ArticlePreviewListDto {
        private List<ArticlePreviewDto> articles;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class ArticlePagePreviewListDto {
        private List<ArticlePreviewDto> articles;
        private boolean hasNext;
        private Long cursor;
    }
}
