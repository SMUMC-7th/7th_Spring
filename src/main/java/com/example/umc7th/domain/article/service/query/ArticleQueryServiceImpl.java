package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;

    //전체 게시글 조회
    @Override
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    //개별 게시글 조회
    @Override
    public Article getArticle(Long id){
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Article"));
    }
}
