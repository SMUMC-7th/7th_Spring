package com.example.umc7th.domain.article.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    public record ArticlePagePreviewDTO(
            List<ArticlePreviewDTO> articleList,
            boolean hasNext,
            Long cursor
    ){}

}
