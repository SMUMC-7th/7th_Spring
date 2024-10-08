package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<Reply> getRepliesByArticle(Long articleId) {
        Article article=articleRepository.findById(articleId).orElseThrow(() -> new EntityNotFoundException("게시물 없음"));
        return replyRepository.findByArticle(article);
    }
}
