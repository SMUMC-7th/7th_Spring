package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Data;
import lombok.Getter;

@Data
public class ArticleRequestDTO {
    public static Article from(ArticleRequestDTO.CreateArticleDTO dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likeNum(0)
                .build();
    }

    @Data
    @Getter
    public static class CreateArticleDTO {
        private final String title;
        private final String content;

    }
}
