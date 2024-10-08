package com.example.umc7th.domain.article.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ArticleResponseDTO {
    @Builder
    public record CreateArticleResponseDto(
            Long id,
            String title,
            String content,
            Integer likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
    }
}
