package com.example.umc7th.article.service.query;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Slice;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.entity.Reply;

public interface ArticleQueryService {
    Article getArticle(Long id);
    List<Article> getArticles();
    boolean hasReplies(Long articleId);
    List<Reply> getRepliesWithOffsetPagination(Long articleId, int page, int size);
    List<Reply> getRepliesWithCursorPagination(Long articleId, Long lastReplyId, int size);
    Slice<Article> getArticlesByIdCursor(Long cursorId, int size);
    Slice<Article> getArticlesByDateCursor(LocalDateTime cursorDate, int size);
    Slice<Article> getArticlesByLikesCursor(Integer likesCount, Long id, int size);
}