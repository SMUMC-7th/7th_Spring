package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
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
        // dto를 Entity로 변환하여 게시글 생성 후 저장
        return articleRepository.save(dto.toEntity());
    }

    @Override
    @Transactional
    public Article updateArticle(Long id, ArticleRequestDTO.UpdateArticleDTO dto) {
        // ID로 게시글 조회하고 없을 경우 예외 발생
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
        // 게시글 업데이트
        article.update(dto.getTitle(), dto.getContent());
        // 게시글 저장
        return articleRepository.save(article);
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        // ID로 게시글 조회하고 없을 경우 예외 발생
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
        // 게시글과 연관된 댓글도 함께 삭제됨
        articleRepository.delete(article);
    }
}
