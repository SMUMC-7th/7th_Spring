package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ArticleRequestDTO {

    @Getter // 필드에 관한 게터 메서드를 만든다.
    @Setter
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

        public void updateEntity(Article article) {
            article.setTitle(this.title);
            article.setContent(this.content);
        }
    }
}