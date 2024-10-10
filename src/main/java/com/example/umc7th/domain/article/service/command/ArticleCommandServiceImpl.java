package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //모든 메소드가 하나의 transaction 단위로 동작, 단일 메소드에도 선언 가능
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.ArticlePreviewDTO createArticle(ArticleRequestDTO.CreateArticleRequestDTO requestDTO){
        Article newArticle = articleRepository.save(ArticleConverter.toEntity(requestDTO));
        return ArticleConverter.from(newArticle);
    }

    @Override
    public ArticleResponseDTO.ArticlePreviewDTO updateArticle(Long articleId,ArticleRequestDTO.UpdateArticleRequestDTO requestDTO){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.update(requestDTO);
        return ArticleConverter.from(article);
    }

    @Override
    public ArticleResponseDTO.ArticlePreviewDTO updateArticleLikenum(Long articleId){
        Article articlelikeNum = articleRepository.findById(articleId)
                .orElseThrow(()-> new ArticleException(ArticleErrorCode.NOT_FOUND));
        articlelikeNum.updateLikenum();
        return ArticleConverter.from(articlelikeNum);
    }

    @Override
    public void deleteArticle(Long articleId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
        article.softDelete();
    }
}
