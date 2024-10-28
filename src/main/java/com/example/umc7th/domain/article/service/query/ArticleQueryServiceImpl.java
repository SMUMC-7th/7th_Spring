package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
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
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }


    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));//에외를 던지면 ExceptionAdvice가 해당 에러를 잡아서 처리
    }
    @Override
    public Slice<Article> getPagedArticles(Long cursor, int offset, String sort) {
        int size = 5;
        Pageable pageable = PageRequest.of(0,size);
        switch (sort){
            case "id":
                return articleRepository.findAllByIdLessThanOrderByIdDesc(cursor,pageable);
            case "createdAt":
                return articleRepository.findAllByCreatedAtLessThanOrderByCreatedAtDesc(cursor,pageable);
            case "like":
                return articleRepository.findAllByLikeNumLessThanOrderByLikeNumDesc(cursor,pageable);
            default:
                throw new ArticleException(ArticleErrorCode.WRONG_TYPE);
        }

    }
    @Override
    public Slice<Article> getPagedArticlesByTitle(String title, Long cursor, int offset) {
        int size = 5;
        Pageable pageable = PageRequest.of(0, size);
        return articleRepository.findByTitleContainingOrderByIdDesc(title,cursor,pageable);
    }
}