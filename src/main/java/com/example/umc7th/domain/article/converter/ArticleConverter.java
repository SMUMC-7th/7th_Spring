package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleConverter {
    //DTO -> Entity
    public static Article toEntity(ArticleRequestDTO.CreateArticleRequestDTO requestDTO){
        return Article.builder()
                .title(requestDTO.title())
                .content(requestDTO.content())
                .likeNum(0)
                .build();
    }

    //Entity -> DTO
    public static ArticleResponseDTO.CreateArticleResponseDto from(Article article){
        return ArticleResponseDTO.CreateArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    //Entity리스트 -> DTO리스트
    public static List<ArticleResponseDTO.CreateArticleResponseDto> fromList(List<Article> articles){
        return articles.stream()
                .map(ArticleConverter::from)
                .collect(Collectors.toList());
    }
}
