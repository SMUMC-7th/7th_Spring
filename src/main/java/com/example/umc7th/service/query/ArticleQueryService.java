package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ArticleResponseDto;

import java.util.List;

public interface ArticleQueryService
{
    public List<ArticleResponseDto> getArticles();

    public ArticleResponseDto getArticle(Long id);
}
