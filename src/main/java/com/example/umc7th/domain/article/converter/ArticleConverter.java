package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.entity.Article;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleConverter {

    // DTO -> Entity로 변환 메서드
    public static Article toEntity(ArticleReqDto.CreateArticleRequestDto requestDto) {
        return Article.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .likeNum(0)
                .build();
    }

    // Entity -> DTO 변환 메서드
    public static ArticleResDto.CreateArticleResponseDto from(Article article) {
        return ArticleResDto.CreateArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    // Entity 리스트 -> DTO 리스트 변환 메서드
    public static List<ArticleResDto.CreateArticleResponseDto> fromList(List<Article> articles) {
        return articles.stream()
                .map(ArticleConverter::from)
                .collect(Collectors.toList());
    }
}
