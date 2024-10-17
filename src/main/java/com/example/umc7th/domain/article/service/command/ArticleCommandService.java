package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;

public interface ArticleCommandService {

    ArticleResDto.CreateArticleResponseDto createArticle(ArticleReqDto.CreateArticleRequestDto requestDto);
    void updateArticle(Long articleId, ArticleReqDto.UpdateArticleRequestDto requestDto);
    void updateArticleTitle(Long articleId, ArticleReqDto.UpdateArticleTitleRequestDto requestDto);
    void updateArticleContent(Long articleId, ArticleReqDto.UpdateArticleContentRequestDto requestDto);
    void deleteArticle(Long articleId);
    ArticleResDto.ArticleLikeResponseDto increaseLikeNum(Long articleId);
}
