package com.example.umc7th.article.repository;

import com.example.umc7th.article.entity.Article;
import org.springframework.data.domain.Slice;

public interface ArticleRepositoryCustom {
    Slice<Article> search(Long cursorId, int size, String likeTitle, boolean isLikedSort);
}
