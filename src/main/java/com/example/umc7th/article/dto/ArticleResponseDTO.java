package com.example.umc7th.article.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDTO {
    List<DetailArticleResponseDTO> articles;

    public ArticleResponseDTO(List<Article> articles) {
        this.articles
                = articles.stream()
                .map(DetailArticleResponseDTO::new)
                .toList();
    }
}
