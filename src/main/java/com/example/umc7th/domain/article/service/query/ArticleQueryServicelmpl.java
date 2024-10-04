package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServicelmpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleResDto.CreateArticleResponseDto> getArticleList() {
        List<Article> articles = articleRepository.findAll();
        return ArticleConverter.fromList(articles);
    }

    @Override
    public ArticleResDto.CreateArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return ArticleConverter.from(article);
    }
}
