package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND_404));
    }

    @Override
    public boolean hasComments(Long id) {
        return replyRepository.existsByArticleId(id);
    }
}