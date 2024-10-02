package com.example.umc7th.domain.article.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ArticleResDto {

    @Builder
    public record CreateArticleResponseDto(
            Long id,
            String title,
            String content,
            int likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }
}
