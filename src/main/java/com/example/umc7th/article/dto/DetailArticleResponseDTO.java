package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private int likeNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DetailArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.likeNum = article.getLikeNum();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }

}
