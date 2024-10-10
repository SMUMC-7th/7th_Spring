package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ArticleResponseDTO {
    List<DetailArticleResponseDTO> articles;

    public static ArticleResponseDTO from(List<Article> articles) {
        return new ArticleResponseDTO(articles.stream()
                .map(DetailArticleResponseDTO::from)
                .toList());
    }

}
