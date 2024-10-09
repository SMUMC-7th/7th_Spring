package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ArticleResponseDto;

public interface ArticleQueryService
{
    public ArticleResponseDto.ArticlePreviewListDto getArticles();

    public ArticleResponseDto.ArticlePreviewDto getArticle(Long id);
}
