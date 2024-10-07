package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    // 댓글 하나 조회
    public Reply getReply(Long replyId) {

        return replyRepository.findById(replyId).orElseThrow(() -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
    }

    @Override
    // 특정 게시글의 댓글 전체 조회
    public List<Reply> getReplies(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        return replyRepository.findByArticle(article);
    }
}
