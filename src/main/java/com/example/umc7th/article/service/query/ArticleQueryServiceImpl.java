package com.example.umc7th.article.service.query;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.exception.ArticleErrorCode;
import com.example.umc7th.article.exception.ArticleException;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//수정이 안되도록 readOnly를 붙여서 작성함
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;


    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public boolean hasReplies(Long articleId) {
        return replyRepository.existsByArticleId(articleId);
    }
}
