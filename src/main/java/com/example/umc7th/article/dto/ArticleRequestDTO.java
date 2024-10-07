package com.example.umc7th.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class ArticleRequestDTO {

    @Getter // 필드에 관한 게터 메서드를 만든다.
    public static class CreateArticleDTO {
        private String title;
        private String content;
    }
}