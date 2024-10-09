package com.example.umc7th.dto.request;


import com.example.umc7th.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ArticleRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateArticleRequestDto {
        private String title;
        private String content;
    }

    // 전체 수정
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateArticleRequestDto {
        private String title;
        private String content;
    }

    // 부분 수정
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartialUpdateArticleRequestDto {
        private String title;
        private String content;
    }
}
