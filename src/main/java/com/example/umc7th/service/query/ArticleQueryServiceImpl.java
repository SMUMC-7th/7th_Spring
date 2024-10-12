package com.example.umc7th.service.query;

import com.example.umc7th.converter.ArticleConverter;
import com.example.umc7th.dto.response.ArticleResponseDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.exception.code.ArticleErrorCode;
import com.example.umc7th.exception.exception.ArticleException;
import com.example.umc7th.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDto.ArticlePreviewListDto getArticles() {
        return ArticleConverter.fromList(articleRepository.findByActiveTrue());
    }

    @Override
    public ArticleResponseDto.ArticlePreviewDto getArticle(Long id) {
        Article article = articleRepository.findByIdAndActiveTrue(id).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        return ArticleConverter.from(article);
    }
}
