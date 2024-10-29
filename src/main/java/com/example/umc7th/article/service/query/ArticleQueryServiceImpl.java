package com.example.umc7th.article.service.query;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
//조회는 Query
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    @Override
    public Article getDetailArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new CustomException(GeneralErrorCode.ARTICLE_NOT_FOUND_404));
    }

    @Override
    public List<Article> getArticles(Long cursorId, String likeTitle) {
        Pageable pageable = PageRequest.of(0, 10);
        Slice<Article> slice = checkLikeTitle(cursorId, likeTitle, pageable);
        List<Article> articles = slice.getContent();
        while (slice.hasNext()) {
            slice = checkLikeTitle(cursorId, likeTitle, slice.nextPageable());
            articles.addAll(slice.getContent());
        }
        return articles;

    }

    private Slice<Article> checkLikeTitle(Long cursorId, String likeTitle, Pageable pageable) {
        if (likeTitle != null && !likeTitle.trim().isEmpty()) {
            return articleRepository.findAllByIdLessThanAndTitleContainingOrderByIdDesc(cursorId,
                    pageable, likeTitle);
        }
        return articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
    }
}
