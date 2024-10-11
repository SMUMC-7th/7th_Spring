package com.example.umc7th.article.service.command;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.exception.ArticleErrorCode;
import com.example.umc7th.article.exception.ArticleException;
import com.example.umc7th.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;


    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(dto.toEntity());
    }

    @Override
    public Article updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.update(dto.getTitle(), dto.getContent());
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        articleRepository.delete(article);
    }
}
