package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.request.ArticleReqDto;
import com.example.umc7th.domain.article.dto.response.ArticleResDto;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleCommandServicelmpl implements ArticleCommandService{

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResDto.CreateArticleResponseDto createArticle(ArticleReqDto.CreateArticleRequestDto requestDto) {
        Article newArticle = articleRepository.save(ArticleConverter.toArticle(requestDto));
        return ArticleConverter.toCreateArticleResponseDto(newArticle);
    }
}
