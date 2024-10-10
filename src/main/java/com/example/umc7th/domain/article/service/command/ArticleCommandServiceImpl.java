package com.example.umc7th.domain.article.service.command;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional //모든 메소드가 하나의 transaction 단위로 동작, 단일 메소드에도 선언 가능
@RequiredArgsConstructor
public class ArticleCommandServiceImpl implements ArticleCommandService{
    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO.CreateArticleResponseDto createArticle(ArticleRequestDTO.CreateArticleRequestDTO requestDTO){
        Article newArticle = articleRepository.save(ArticleConverter.toEntity(requestDTO));
        return ArticleConverter.from(newArticle);
    }
}
