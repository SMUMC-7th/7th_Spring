package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    //전체 게시글 조회
    @Override
    public List<ArticleResponseDTO.ArticlePreviewDTO> getArticleList(){
        List<Article> articles = articleRepository.findByActiveTrue();
        return ArticleConverter.fromList(articles);
    }

    //개별 게시글 조회
    @Override
    public ArticleResponseDTO.ArticlePreviewDTO getArticle(Long articleId){
        Article article = articleRepository.findByIdAndActiveTrue(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
        return ArticleConverter.from(article);
    }
}
