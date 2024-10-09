package com.example.umc7th.domain.article.dto.request;

public class ArticleReqDto {

    public record CreateArticleRequestDto(
            String title,
            String content
    ) {
    }
}
