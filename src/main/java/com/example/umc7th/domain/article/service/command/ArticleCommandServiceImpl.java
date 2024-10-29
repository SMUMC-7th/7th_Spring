package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Service로 사용하겠다고 명시 (빈 주입)
@Service
// Transactional을 사용하겠다고 명시. 모든 메소드가 하나의 Transaction 단위로 동작, 단일 메소드에도 선언 가능
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;
    private final ReplyCommandService replyCommandService;

    @Override
    // 게시글 생성
    public Article createArticle(ArticleRequestDTO.CreateArticleDTO dto) {

        Article article = ArticleConverter.toArticle(dto);
        articleRepository.save(article);

        return article;
    }

    @Override
    // 게시글 수정
    public void updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO updateArticleDTO) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.update(updateArticleDTO.getTitle(), updateArticleDTO.getTitle());
    }

    @Override
    // 게시글 hard 삭제
    public void hardDeleteArticle(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        // 해당 Article에 속하는 댓글들 삭제
        replyRepository.deleteByArticleId(articleId);
        articleRepository.delete(article);
    }

    @Override
    // 게시글 soft 삭제
    public void softDeleteArticle(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        // 해당 article에 속하는 reply들 soft 삭제
        replyRepository.findByArticle(article)
                .forEach(reply -> replyCommandService.softDeleteReply(reply.getId()));
        article.setIsDeleted(true);
    }

    @Override
    // 게시글 좋아요 수 증가
    public void increaseLike(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        article.increaseLikeNum();
    }

}