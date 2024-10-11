package com.example.umc7th.domain.article.dto;

import lombok.Getter;

public class ArticleRequestDTO {

    @Getter
    // Article 생성에 필요한 데이터
    public static class CreateArticleDTO {
        private String title;
        private String content;
    }

    @Getter
    // Article 수정에 필요한 데이터
    public static class UpdateArticleDTO {
        private String title;
        private String content;
    }

}
