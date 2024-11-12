package com.example.umc7th.article.service.query;

import com.example.umc7th.article.dto.DetailArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
//조회는 Query
public interface ArticleQueryService {
    Article getDetailArticle(Long id);

    Slice<DetailArticleResponseDTO> getArticles(Long cursorId, int size, String likeTitle, boolean isLikedSort);
}
