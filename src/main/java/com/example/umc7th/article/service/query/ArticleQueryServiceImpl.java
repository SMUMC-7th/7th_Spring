package com.example.umc7th.article.service.query;

import com.example.umc7th.article.converter.ArticleConverter;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
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
    public ArticleResponseDTO getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new GeneralException(GeneralErrorCode.ARTICLE_NOT_FOUND_404));
        return ArticleConverter.toArticleResponseDto(article);
    }

    @Override
    public List<ArticleResponseDTO> getArticles() {
        List<Article> articleList = articleRepository.findAll();
        return ArticleConverter.toArticleResponseDtoList(articleList);
    }
}