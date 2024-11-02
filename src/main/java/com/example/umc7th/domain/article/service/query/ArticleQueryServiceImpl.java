package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.enums.ArticleSearchQuery;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));//에외를 던지면 ExceptionAdvice가 해당 에러를 잡아서 처리
    }


    @Override
    public Slice<Article> getArticles(Long cursor, int offset, String sort) {
        Pageable pageable = PageRequest.of(0, offset);
        if (sort.equals(ArticleSearchQuery.ID.name())) {
            if (cursor.equals(0L)) {
                return articleRepository.findAllByOrderByIdDesc(pageable);//첫 검색이면 0값을 주어 첫검색 표기
            }
            return articleRepository.findAllByIdLessThanOrderByIdDesc(cursor, pageable);
        }
        else if (sort.equals(ArticleSearchQuery.LIKE.name())) {
            if (cursor.equals(0L)) {
                return articleRepository.findAllByOrderByLikeNumDescIdDesc(pageable);
            }
            return articleRepository.findAllByOrderByLikeWithCursor(cursor, pageable);
        }
        else {
            throw new ArticleException(ArticleErrorCode.WRONG_TYPE);
        }

    }
    @Override
    public List<Article> getPagedArticlesByTitle(String title, String content) {
        return articleRepository.findAllByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(title,content);
    }
}