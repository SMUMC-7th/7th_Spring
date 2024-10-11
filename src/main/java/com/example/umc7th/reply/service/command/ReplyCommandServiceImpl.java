package com.example.umc7th.reply.service.command;

import org.springframework.stereotype.Service;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.exception.ArticleErrorCode;
import com.example.umc7th.article.exception.ArticleException;
import com.example.umc7th.article.exception.ArticleNotFoundException;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.converter.ReplyConverter;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.exception.ReplyErrorCode;
import com.example.umc7th.reply.exception.ReplyException;
import com.example.umc7th.reply.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;


    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
            new ArticleException(ArticleErrorCode.NOT_FOUND));
        return replyRepository.save(ReplyConverter.toReply(dto, article));
    }
    @Override
    public Reply updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
            new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.setContent(dto.getContent()); // Update the reply's content
        return replyRepository.save(reply); // Save the updated reply
    }

    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
            new ReplyException(ReplyErrorCode.NOT_FOUND));
        replyRepository.delete(reply);
    }
}