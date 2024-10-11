package com.example.umc7th.domain.article.dto;

import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;
    }

    @Getter
    public static class UpdateArticleDTO {
        private Long articleId;
        private String title;
        private String content;
    }
}
