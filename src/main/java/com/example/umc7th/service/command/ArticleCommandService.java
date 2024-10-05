package com.example.umc7th.service.command;

import com.example.umc7th.dto.request.ArticleRequestDto;

public interface ArticleCommandService {
    public Long createArticle(ArticleRequestDto articleRequestDto);


}
