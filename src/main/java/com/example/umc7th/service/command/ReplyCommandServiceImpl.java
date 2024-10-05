package com.example.umc7th.service.command;


import com.example.umc7th.dto.request.ReplyRequestDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
import com.example.umc7th.global.apipayload.code.GeneralErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;
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
    public Long createReply(ReplyRequestDto replyRequestDto, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new GeneralException(GeneralErrorCode.ARTICLE_NOT_FOUND));
        Reply reply = replyRequestDto.toEntity(article);
        replyRepository.save(reply);
        return reply.getId();
    }
}
