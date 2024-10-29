package com.example.umc7th.domain.reply.service.command;



import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;
    //댓글 생성
    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        Article article=articleRepository.findById(dto.getArticleId()).orElseThrow(() -> new EntityNotFoundException("게시물 없음"));
        return replyRepository.save(
                // Builder 패턴 사용
//                Reply.builder()
//                        .content(dto.getContent())
//                        .article(article)
//                        .build()
                //dto사용
                ReplyConverter.toReply(dto, article)
        );
    }
    // 댓글 수정
    @Override
    public Reply updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.update(dto.getContent());
        return reply;
    }

    // 댓글 softDelete
    @Override
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다."));
        reply.softDelete(); //더티체킹되어서 자동 DB반영
    }


}
