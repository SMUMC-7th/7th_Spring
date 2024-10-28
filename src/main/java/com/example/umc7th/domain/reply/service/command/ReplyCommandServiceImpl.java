package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService{
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public ReplyResponseDTO.ResponsePreviewDto createReply(ReplyRequestDTO.CreateReplyDTO requestDTO){
        Article article = articleRepository.findById(requestDTO.articleId())
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));

        Reply savedReply = replyRepository.save(ReplyConverter.toEntity(requestDTO,article));
        return ReplyConverter.from(savedReply);
    }

    @Override
    public ReplyResponseDTO.ResponsePreviewDto updateReply(Long replyId,ReplyRequestDTO.UpdateReplyDTO requestDTO){
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.update(requestDTO);
        return ReplyConverter.from(reply);
    }

    @Override
    public void deleteReply(Long replyId){
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.softDelete();
    }
}
