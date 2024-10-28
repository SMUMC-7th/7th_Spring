package com.example.umc7th.domain.article.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ArticleResponseDTO {
    @Builder
    public record ArticlePreviewDTO(
            Long id,
            String title,
            String content,
            int likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            boolean active
    ){
    }

}
