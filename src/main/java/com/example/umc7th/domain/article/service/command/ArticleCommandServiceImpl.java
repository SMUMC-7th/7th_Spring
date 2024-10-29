package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.article.entity.Article;
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
//        return articleRepository.save(
//                // Builder 패턴 사용
//                Article.builder()
//                        .title(dto.getTitle())
//                        .content(dto.getContent())
//                        .build()
//        );
        //DTO 사용
        return articleRepository.save(dto.toEntity());
    }
    //게시물 수정
    @Override
    public Article updateArticle(Long articleId, ArticleRequestDTO.UpdateArticleDTO dto) {
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.update(dto.getTitle(), dto.getContent());
        return article;
    }
    //게시물 삭제
    @Override
    public void deleteArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
        articleRepository.delete(article);
    }
    //댓글 있으면 삭제 어려움
    //1. hard delete 하기 -> 관련 댓글 다 삭제 시켜버리기
    //2. soft delete 하기
}
