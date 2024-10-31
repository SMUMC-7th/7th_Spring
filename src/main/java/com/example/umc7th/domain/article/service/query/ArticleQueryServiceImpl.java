package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResDto.ArticlePreviewDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return ArticleConverter.toArticlePreviewDto(article);
    }

    @Override
    public ArticleResDto.ArticlePreviewListDto getArticlesByCursor(Long cursor, int offset, String sort) {
        Pageable pageable = PageRequest.of(0, offset);
        Slice<Article> articles;

        // 정렬 기준에 따른 커서 페이지네이션 처리
        switch (sort) {
            case "createdAt":
                articles = getArticlesByCreatedAtCursor(cursor, pageable);
                break;
            case "like":
                articles = getArticlesByLikeNumCursor(cursor, pageable);
                break;
            default:
                articles = getArticlesByIdCursor(cursor, pageable);
        }

        // 다음 커서 계산
        Long nextCursor = calculateNextCursor(articles, sort);

        // 변환하여 반환
        return ArticleConverter.toArticlePreviewListDto(articles, nextCursor);
    }

    // ID 기준 커서 페이지네이션
    private Slice<Article> getArticlesByIdCursor(Long cursor, Pageable pageable) {
        Long cursorId = cursor != null ? cursor : Long.MAX_VALUE;
        return articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
    }

    // 생성 날짜 기준 커서 페이지네이션
    private Slice<Article> getArticlesByCreatedAtCursor(Long cursor, Pageable pageable) {
        LocalDateTime cursorCreatedAt = cursor != null
                ? LocalDateTime.ofInstant(Instant.ofEpochMilli(cursor), ZoneId.systemDefault())
                : LocalDateTime.now();
        return articleRepository.findAllByCreatedAtLessThanOrderByCreatedAtDesc(cursorCreatedAt, pageable);
    }

    // 좋아요 수 기준 커서 페이지네이션
    private Slice<Article> getArticlesByLikeNumCursor(Long cursor, Pageable pageable) {
        int likeNum = cursor != null ? (int) (cursor >> 32) : Integer.MAX_VALUE;
        Long id = cursor != null ? cursor & 0xFFFFFFFFL : Long.MAX_VALUE;
        return articleRepository.findByLikeNumAndIdCursor(likeNum, id, pageable);
    }

    // 다음 커서 계산
    private Long calculateNextCursor(Slice<Article> articles, String sort) {
        if (!articles.hasNext()) {
            return null; // 다음 페이지가 없으면 null 반환
        }

        Article lastArticle = articles.getContent().get(articles.getNumberOfElements() - 1);

        // 정렬 기준에 따라 커서 값 계산
        switch (sort) {
            case "createdAt":
                return lastArticle.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            case "like":
                return ((long) lastArticle.getLikeNum() << 32) | lastArticle.getId();
            default:
                return lastArticle.getId();
        }
    }
}
