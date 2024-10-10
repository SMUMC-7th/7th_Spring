package com.example.umc7th.domain.article.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ArticleResponseDTO {
    @Builder
    public record CreateArticleResponseDto(
            Long id,
            String title,
            String content,
            int likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
    }

    @Builder
    public record UpdateArticleResponseDto(
            String title,
            String content
    ){

    }

    @Builder
    public record UpdateArticleLikenumResponseDto(
            int likeNum
    ){

    }


}
