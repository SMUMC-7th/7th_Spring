package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    // 댓글 생성 메서드
    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        // 댓글이 달릴 게시글이 존재하는지 확인
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        return replyRepository.save(ReplyConverter.toReply(dto, article));
    }

    // 댓글 수정 메서드
    @Override
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto) {
        // 수정할 댓글이 존재하는지 확인
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        reply.update(dto.getContent()); // Dirty Checking
        return reply;
    }

    // 댓글 삭제 메서드
    @Override
    public Long deleteReply(Long id) {
        // 삭제할 댓글이 존재하는지 확인
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        replyRepository.delete(reply);
        return id;
    }
}