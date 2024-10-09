package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;

public interface ArticleCommandService {

    ArticleResDto.CreateArticleResponseDto createArticle(ArticleReqDto.CreateArticleRequestDto requestDto);
}
