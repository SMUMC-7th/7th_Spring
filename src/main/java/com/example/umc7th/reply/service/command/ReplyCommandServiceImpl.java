package com.example.umc7th.reply.service.command;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public Reply createReply(Long id, ReplyRequestDTO.CreateReplyDTO dto) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new CustomException(GeneralErrorCode.ARTICLE_NOT_FOUND_404)
        );
        Reply reply = Reply.builder()
                .article(article)
                .content(dto.content())
                .build();
        replyRepository.save(reply);
        return reply;
    }

    @Override
    public void updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto) {
        Reply reply
                = replyRepository.findById(id).orElseThrow(
                () -> new CustomException(GeneralErrorCode.NOT_FOUND_404));
        reply.update(dto.content());
    }

    @Override
    public void deleteReply(Long id) {
        Reply reply
                = replyRepository.findById(id).orElseThrow(
                () -> new CustomException(GeneralErrorCode.NOT_FOUND_404));
        reply.softDelete();
    }
    /// 해당 함수가 끝날 때 Service class에 @Transactional을 붙여 놓아서 Transaction이 커밋됩니다.
    //  이후 더티 체킹으로 변경 사항이 데이터베이스에 반영됩니다.

}
