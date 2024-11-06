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

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        // 주어진 articleId로 게시글을 조회하고 없으면 예외 발생
        Article article = articleRepository.findById(dto.getArticleId()).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));
        // 엔티티 생성 후 저장
        return replyRepository.save(ReplyConverter.toReply(dto, article));
    }

    @Override
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto) {
        // 주어진 id로 댓글을 조회하고 없으면 예외 발생
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
        // 댓글 내용 업데이트
        reply.update(dto.getContent());
        // 변경된 댓글 저장
        return replyRepository.save(reply);
    }

    @Override
    public Reply deleteReply(Long id) {
        // 댓글을 삭제하고 null 반환
        replyRepository.deleteById(id);
        return null;
    }
}