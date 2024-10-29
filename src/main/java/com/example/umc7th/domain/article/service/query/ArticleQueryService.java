package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.entity.enums.SortType;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ArticleQueryService {

    Article getArticle(Long id);
    List<Article> getArticles();
    Slice<Article> getArticlesBySort(SortType sortType, Long cursorId);
    Slice<Article> getArticlesByIdDesc(Long cursorId);
    Slice<Article> getArticlesByLikeNumDesc(Long cursorId);
}
