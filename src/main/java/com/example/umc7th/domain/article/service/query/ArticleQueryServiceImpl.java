package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}