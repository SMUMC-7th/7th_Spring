package com.example.umc7th.article.service.query;

import com.example.umc7th.article.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//조회는 Query
public interface ArticleQueryService {
    Article getDetailArticle(Long id);

    List<Article> getArticles();
}
