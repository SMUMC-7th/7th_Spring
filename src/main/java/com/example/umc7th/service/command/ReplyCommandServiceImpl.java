package com.example.umc7th.service.command;


import com.example.umc7th.converter.ReplyConverter;
import com.example.umc7th.dto.request.ReplyRequestDto;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
import com.example.umc7th.exception.code.ArticleErrorCode;
import com.example.umc7th.exception.code.ReplyErrorCode;
import com.example.umc7th.exception.exception.ArticleException;
import com.example.umc7th.exception.exception.ReplyException;
import com.example.umc7th.repository.ArticleRepository;
import com.example.umc7th.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Long createReply(ReplyRequestDto.CreateReplyRequestDto replyRequestDto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        Reply reply = ReplyConverter.toEntity(replyRequestDto ,article);
        replyRepository.save(reply);
        return reply.getId();
    }

    @Override
    public ReplyResponseDto.ReplyPreviewDto updateReply(Long replyId, ReplyRequestDto.UpdateReplyRequestDto dto) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
        reply.update(dto);
        return ReplyConverter.from(reply);
    }

    @Override
    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }


}
