package com.example.umc7th.article.service.command;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.error.ArticleErrorCode;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {
    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        Article article = ArticleRequestDTO.from(dto);
        articleRepository.save(article);
        return article;
    }

    @Override
    @Transactional
    public void updateArticle(Long articleId, ArticleRequestDTO.UpdateReplyDTO dto) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomException(GeneralErrorCode.NOT_FOUND_404)
        );
        article.updateTitle(dto.title());
        article.updateContent(dto.content());
    }

    @Override
    @Transactional
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomException(GeneralErrorCode.NOT_FOUND_404)
        );
        article.softDelete();
    }

    @Override
    @Transactional
    public void addLikeNum(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new CustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404)
        );
        article.addLikeNumber();
        articleRepository.save(article);
    }

}
