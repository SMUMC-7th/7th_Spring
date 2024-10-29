package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();

    //해당 게시물 댓글 존재 확인
    boolean hasReply(Long id);

    //커서기반 id기준 페이지네이션
    Slice<Article> getArticlesAfterCursorById(Long cursorId, Pageable pageable);
}

