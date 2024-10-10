package com.example.umc7th.article.service.query;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }
}
