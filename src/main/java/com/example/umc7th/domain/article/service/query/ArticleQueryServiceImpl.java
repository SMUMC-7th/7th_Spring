package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.entity.enums.SortType;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    private static final int PAGE_SIZE = 10;

    @Override
    // 게시글 하나 조회
    public List<Article> getArticles() {

        return articleRepository.findAll();
    }

    @Override
    // 게시글 전체 조회
    public Article getArticle(Long id) {

        return articleRepository.findById(id).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    // 정렬 기준에 따라 구분하여 각 로직 호출
    public Slice<Article> getArticlesBySort(SortType sortType, Long cursorId) {

        return switch (sortType) {
            case ID_DESC ->
                // 게시글 아이디 내림차순 정렬
                getArticlesByIdDesc(cursorId);
            case LIKE_NUM_DESC ->
                // 좋아요수 내림차순 정렬
                getArticlesByLikeNumDesc(cursorId);
        };
    }

    @Override
    public Slice<Article> getArticlesByIdDesc(Long cursorId) {

        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        // cursorId 가 0일경우 == 처음 조회하는 경우
        if (cursorId == 0L) {
            return articleRepository.findFirstPageByIdDesc(pageable);
        }

        articleRepository.findById(cursorId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        return articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
    }

    @Override
    public Slice<Article> getArticlesByLikeNumDesc(Long cursorId) {

        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        // cursorId 가 0일경우 == 처음 조회하는 경우
        if (cursorId == 0L) {
            return articleRepository.findFirstPageByLikeNumDescIdDesc(pageable);
        }

        Article article = articleRepository.findById(cursorId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        return articleRepository.findAllByOrderByLikeNum(article.getLikeNum(), cursorId, pageable);
    }

}
