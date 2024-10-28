package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new CustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404));
        Reply reply = ReplyConverter.toReply(dto, article);
        return replyRepository.save(reply);
    }

    @Override
    public Reply updateReply(ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(dto.getReplyId()).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND_404));
        // 데이터베이스에서 찾아 영속 상태인 엔티티를 가져옴
        reply.update(dto.getContent()); // 영속 상태의 엔티티의 내용을 변경
        return reply; // 변경된 엔티티를 반환
    }

    @Override
    public Long deleteReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND_404));
        if (!reply.isActivated()) {
            throw new ReplyException(ReplyErrorCode.REPLY_DELETED_410);
        }
        reply.softDelete();
        return reply.getId();
    }
}
