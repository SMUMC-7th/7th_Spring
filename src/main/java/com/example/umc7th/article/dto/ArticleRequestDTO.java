package com.example.umc7th.article.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ArticleRequestDTO {
    @Data
    @Getter
    public static class CreateArticleDTO {
        private final String title;
        private final String content;
        
    }
}
