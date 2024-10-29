package com.example.umc7th.article.service.query;

import com.example.umc7th.article.entity.Article;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
//조회는 Query
public interface ArticleQueryService {
    Article getDetailArticle(Long id);

    List<Article> getArticles(Long cursorId, String likeTitle);
}
