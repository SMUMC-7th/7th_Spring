package com.example.umc7th.article.service.query;

import com.example.umc7th.article.dto.ArticleRequestDTO;
import com.example.umc7th.article.dto.ArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService{

    private final ArticleRepository articleRepository;

    //id로 Article 조회
    @Override
    public ArticleResponseDTO getArticle(Long id) {
        Optional<Article> articleOptional = articleRepository.findArticleById(id);
        return articleOptional.map(ArticleResponseDTO::new).orElse(null);
    }

    //전체 article 조회
    @Override
    public List<ArticleResponseDTO> getArticles() {
        List<Article> articles = articleRepository.findAll();

        List<ArticleResponseDTO> articleResponseDTOList =
                articles.stream().map(article -> new ArticleResponseDTO(article)).collect(Collectors.toList());
        return articleResponseDTOList;
    }
}
