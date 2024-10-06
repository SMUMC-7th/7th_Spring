package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;
    private int likeNum;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.likeNum = article.getLikeNum();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
