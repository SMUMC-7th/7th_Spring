package com.example.umc7th.domain.article.dto;

public class ArticleRequestDTO {

    public record CreateArticleRequestDTO(
        String title,
        String content){
    }

    public record UpdateArticleRequestDTO(
            String title,
            String content){

    }

    public record UpdateArticleLikenumRequestDTO(
            int likeNum
    ){}
}
