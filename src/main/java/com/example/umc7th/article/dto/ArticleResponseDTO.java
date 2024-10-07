package com.example.umc7th.article.dto;

import java.time.LocalDateTime;

import com.example.umc7th.article.entity.Article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
