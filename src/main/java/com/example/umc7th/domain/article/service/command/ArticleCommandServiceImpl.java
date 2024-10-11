package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
// 모든 메소드가 하나의 Transaction 단위로 동작, 단일 메소드에도 선언 가능
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;

    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(ArticleConverter.toArticle(dto));
    }

    @Override
    public Article updateArticle(ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new CustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404)
        );
        article.update(dto.getTitle(), dto.getContent());
        return article;
    }

    @Override
    public Long deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new CustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404)
        );
        articleRepository.delete(article);
        return article.getId();
    }

}