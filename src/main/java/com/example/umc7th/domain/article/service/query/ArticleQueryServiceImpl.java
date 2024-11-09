package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.enums.ArticleSearchQuery;
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
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public Slice<Article> getArticles(String query, Long cursor, Integer offset) {
        // 페이지 요청을 생성하여 한 페이지에 가져올 데이터 개수를 설정
        Pageable pageable = PageRequest.of(0, offset);
        // 쿼리 값이 "ID"일 경우 ID 순서로 조회
        if (query.equals(ArticleSearchQuery.ID.name())) {
            if (cursor.equals(0L)) { // 커서가 0일 경우 첫 페이지로 조회
                return articleRepository.findAllByOrderByIdDesc(pageable);
            }
            // 커서가 0이 아닐 경우, 주어진 커서보다 작은 ID를 기준으로 조회
            return articleRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);
        }

        // 쿼리 값이 "LIKE"일 경우 좋아요 순서로 조회
        else if (query.equals(ArticleSearchQuery.LIKE.name())) {
            if (cursor.equals(0L)) { // 커서가 0일 경우 첫 페이지로 조회
                return articleRepository.findAllByOrderByLikeNumDescIdDesc(pageable);
            }
            // 커서가 0이 아닐 경우, 주어진 커서보다 작은 ID를 기준으로 조회
            return articleRepository.findAllByOrderByLikeWithCursor(cursor, pageable);
        }
        // 지원하지 않는 쿼리일 경우 예외 발생
        else {
            throw new ArticleException(ArticleErrorCode.UNSUPPORTED_QUERY);
        }

    }

    @Override
    public Article getArticle(Long id) {
        // 주어진 ID로 Article 엔티티를 조회하고, 없으면 ArticleException 예외 발생
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
    }
}