package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    public static class CreateArticleDTO{
        private String title;
        private String content;

        public Article toEntity() {
            return Article.builder()
                    .title(this.getTitle())
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

    @Getter
    public static class DeleteArticleDTO {
        public String id;
    }
}
