package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Data;

@Data
public class ArticleRequestDTO {
    public static Article from(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.title())
                .content(dto.content())
                .likeNum(0)
                .build();
    }

    public record CreateArticleDTO(String title, String content) {
    }

    public record UpdateReplyDTO(String title, String content) {
    }
}
