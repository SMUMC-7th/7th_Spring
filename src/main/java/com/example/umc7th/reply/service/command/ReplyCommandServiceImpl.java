package com.example.umc7th.reply.service.command;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.exception.ArticleErrorCode;
import com.example.umc7th.article.exception.ArticleException;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.exception.ReplyErrorCode;
import com.example.umc7th.reply.exception.ReplyException;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;


    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));

        return replyRepository.save(dto.toEntity(article));

    }

    @Override
    public Reply updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.update(dto.getContent());
        return replyRepository.save(reply);
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        replyRepository.delete(reply);
    }
}
