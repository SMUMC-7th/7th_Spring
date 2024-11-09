package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 서비스 계층의 빈으로 등록되도록
@Transactional // 클래스 내 모든 메서드가 트랜잭션 내에서 실행되도록
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;

    /** 게시물 CREATE */
    @Override
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {
        return articleRepository.save(dto.toEntity());
    }

    /** 게시물 UPDATE */
    @Override
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        // dirty checking 하므로 entity로 update
        article.update(dto.getTitle(), dto.getContent());
        return article;
    }

    /** 좋아요 UPDATE - INCREASE */
    @Override
    public Article increaseLike(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        // dirty checking 하므로 entity로 update
        article.increaseLike();
        return article;
    }

    /** 게시물 DELETE */
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}