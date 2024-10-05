package com.example.umc7th.service.command;

import com.example.umc7th.dto.request.ArticleRequestDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService{
    private final ArticleRepository articleRepository;

    @Override
    public Long createArticle(ArticleRequestDto articleRequestDto) {
        Article article = articleRequestDto.toEntity();
        articleRepository.save(article);
        return article.getId();
    }

}
