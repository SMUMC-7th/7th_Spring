package com.example.umc7th.article.dto;

import org.springframework.data.domain.Slice;

public record ArticleResponseDTO(Slice<DetailArticleResponseDTO> articles) {
    public static ArticleResponseDTO from(Slice<DetailArticleResponseDTO> articles) {
        return new ArticleResponseDTO(articles);
    }

}
