package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DetailArticleResponseDTO(Long id, String title, String content, int likeNum, LocalDateTime createdAt,
                                       LocalDateTime updatedAt) {
    public static DetailArticleResponseDTO from(Article article) {
        return DetailArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }


}
