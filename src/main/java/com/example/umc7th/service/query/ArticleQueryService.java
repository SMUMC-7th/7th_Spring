package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ArticleResponseDto;
import com.example.umc7th.entity.Article;
import org.springframework.data.domain.Slice;

public interface ArticleQueryService
{
    public ArticleResponseDto.ArticlePreviewListDto getArticles();

    public ArticleResponseDto.ArticlePreviewDto getArticle(Long id);

    public Slice<Article> getArticlesPage(Long cursor, int pageSize);

}
