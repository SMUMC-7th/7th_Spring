package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;

import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO {
        private String title;
        private String content;

        public Article toEntity() {
            return Article.builder()
                    .title(this.title)
                    .content(this.getContent())
                    .likeNum(0)
                    .build();
        }
    }

    @Getter
    public static class UpdateArticleDTO {
        private String title;
        private String content;

    }
}
