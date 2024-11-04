package com.example.umc7th.domain.article.converter;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;

import java.util.List;

@Slf4j
public class ArticleConverter {

    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO createArticleDTO) {

        return Article.builder()
                .title(createArticleDTO.getTitle())
                .content(createArticleDTO.getContent())
                .likeNum(0)
                .build();
    }

    public static ArticleResponseDTO.CreateArticleResultDTO toCreateArticleResultDTO(Article article) {

        return ArticleResponseDTO.CreateArticleResultDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .build();
    }

    public static ArticleResponseDTO.ArticleViewDTO toArticleViewDTO(Article article) {

        return ArticleResponseDTO.ArticleViewDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeNum(article.getLikeNum())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    public static ArticleResponseDTO.ArticleViewListDTO toArticleViewListDTO(List<Article> articles) {

        List<ArticleResponseDTO.ArticleViewDTO> articleViewDTOs = articles.stream()
                .map(ArticleConverter::toArticleViewDTO)
                .toList();

        return ArticleResponseDTO.ArticleViewListDTO.builder()
                .articleViewDTOs(articleViewDTOs)
                .build();
    }

    public static ArticleResponseDTO.ArticleSliceResponse toArticleSliceResponseDTO(Slice<Article> articles) {

        if (articles.getContent() == null) {
            throw new IllegalArgumentException("페이징 목록이 비어있습니다.");
        }

        List<ArticleResponseDTO.ArticleViewDTO> articleViewDTOs = articles.stream()
                .map(ArticleConverter::toArticleViewDTO)
                .toList();

        return ArticleResponseDTO.ArticleSliceResponse.builder()
                .articleViewDTOs(articleViewDTOs)
                .currentPage(articles.getNumber())
                .hasNext(articles.hasNext())
                .pageSize(articles.getSize())
                .numberOfElements(articles.getNumberOfElements())
                .build();
    }

}
