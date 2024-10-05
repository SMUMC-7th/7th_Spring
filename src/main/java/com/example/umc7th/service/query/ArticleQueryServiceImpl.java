package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ArticleResponseDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.global.apipayload.code.GeneralErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;
import com.example.umc7th.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<ArticleResponseDto> getArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleResponseDto::from)
                .toList();
    }

    @Override
    public ArticleResponseDto getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new GeneralException(GeneralErrorCode.ARTICLE_NOT_FOUND));
        return ArticleResponseDto.from(article);
    }
}
