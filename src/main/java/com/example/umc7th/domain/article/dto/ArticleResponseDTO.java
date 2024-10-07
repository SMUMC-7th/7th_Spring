package com.example.umc7th.domain.article.dto;

import com.example.umc7th.domain.article.entity.Article;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;

    public static ArticleResponseDTO toArticleResponseDTO(Article article){
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }
}
