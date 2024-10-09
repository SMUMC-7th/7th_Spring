package com.example.umc7th.converter;

import com.example.umc7th.dto.request.ArticleRequestDto;
import com.example.umc7th.dto.response.ArticleResponseDto;
import com.example.umc7th.entity.Article;

import java.util.List;

public class ArticleConverter {
    public static Article toEntity(ArticleRequestDto.CreateArticleRequestDto dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likeNum(0)
                .build();
    }

    public static ArticleResponseDto.ArticlePreviewDto from(Article article){
        return ArticleResponseDto.ArticlePreviewDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreated_at())
                .updatedAt(article.getUpdated_at())
                .build();
    }

    public static ArticleResponseDto.ArticlePreviewListDto fromList(List<Article> articles) {
        return ArticleResponseDto.ArticlePreviewListDto.builder()
                .articles(articles.stream().map(ArticleConverter::from).toList())
                .build();
    }
}
