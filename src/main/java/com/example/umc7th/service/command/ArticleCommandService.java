package com.example.umc7th.service.command;

import com.example.umc7th.dto.request.ArticleRequestDto;
import com.example.umc7th.dto.response.ArticleResponseDto;

public interface ArticleCommandService {
    public Long createArticle(ArticleRequestDto.CreateArticleRequestDto articleRequestDto);

    public ArticleResponseDto.ArticlePreviewDto updateArticle(Long articleId, ArticleRequestDto.UpdateArticleRequestDto dto);

    public ArticleResponseDto.ArticlePreviewDto partialUpdateArticle(Long articleId, ArticleRequestDto.PartialUpdateArticleRequestDto dto);

    public void deleteArticle(Long articleId);
}
