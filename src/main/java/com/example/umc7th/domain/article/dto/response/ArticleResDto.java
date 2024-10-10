package com.example.umc7th.domain.article.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResDto {

    @Builder
    public record CreateArticleResponseDto(
            Long id,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ArticlePreviewDto(
            Long id,
            String title,
            String content,
            int likeNum,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record ArticlePreviewListDto(
            List<ArticlePreviewDto> articlePreviewDtoList
    ){
    }

    @Builder
    public record ArticleLikeResponseDto(
            Long id,
            int likeNum
    ) {
    }
}
