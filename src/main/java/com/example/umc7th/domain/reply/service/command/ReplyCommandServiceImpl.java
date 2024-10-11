package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        return replyRepository.save(dto.toReply(article));
    }
    @Override
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(id).orElseThrow(()->
                new ReplyException(ReplyErrorCode.NOT_FOUND));

        reply.update(dto.getContent());
        return reply;
    }
    @Override
    public Long deleteReply(Long id) {
        //delete전 있는 데이터인지 확인할 필요가 있다고 생각했음, return값으로 id를 주는 이유는 클라이언트에게 지워진 대상 확인목적?
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        replyRepository.delete(reply);
        return id;
    }
}