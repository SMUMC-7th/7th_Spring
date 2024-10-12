package com.example.umc7th.service.command;

import com.example.umc7th.converter.ArticleConverter;
import com.example.umc7th.dto.request.ArticleRequestDto;
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
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService{
    private final ArticleRepository articleRepository;

    @Override
    public Long createArticle(ArticleRequestDto.CreateArticleRequestDto articleRequestDto) {
        Article article = ArticleConverter.toEntity(articleRequestDto);
        articleRepository.save(article);
        return article.getId();
    }

    @Override
    public ArticleResponseDto.ArticlePreviewDto updateArticle(Long articleId, ArticleRequestDto.UpdateArticleRequestDto dto) {
        Article article = articleRepository.findByIdAndActiveTrue(articleId).orElseThrow(
                () -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.update(dto);
        return ArticleConverter.from(article);
    }

    @Override
    public ArticleResponseDto.ArticlePreviewDto partialUpdateArticle(Long articleId, ArticleRequestDto.PartialUpdateArticleRequestDto dto) {
        Article article = articleRepository.findByIdAndActiveTrue(articleId).orElseThrow(
                () -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.updatePartial(dto);
        return ArticleConverter.from(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findByIdAndActiveTrue(articleId).orElseThrow(
                () -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.softDelete();
    }

    @Override
    public ArticleResponseDto.ArticlePreviewDto increaseLikeNum(Long articleId) {
        Article article = articleRepository.findByIdAndActiveTrue(articleId).orElseThrow(
                () -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.increaseLikeNum();
        return ArticleConverter.from(article);
    }


}
