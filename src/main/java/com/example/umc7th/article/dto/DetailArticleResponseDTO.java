package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DetailArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private int likeNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
