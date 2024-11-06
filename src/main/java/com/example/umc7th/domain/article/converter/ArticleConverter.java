package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
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
                .active(true)
                .build();
    }

    //Entity -> DTO
    public static ArticleResponseDTO.ArticlePreviewDTO from(Article article){
        return ArticleResponseDTO.ArticlePreviewDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .active(article.isActive())
                .build();
    }

    public static ArticleResponseDTO.ArticlePagePreviewDTO from(Slice<Article> articles){
        List<ArticleResponseDTO.ArticlePreviewDTO> articleList = fromList(articles.getContent());
        return ArticleResponseDTO.ArticlePagePreviewDTO.builder()
                .articleList(articleList)
                .hasNext(articles.hasNext())
                .cursor(articles.getContent().get(articles.getContent().size() - 1).getId())
                .build();
    }

    //Entity리스트 -> DTO리스트
    public static List<ArticleResponseDTO.ArticlePreviewDTO> fromList(List<Article> articles){
        return articles.stream()
                .map(ArticleConverter::from)
                .collect(Collectors.toList());
    }
}
