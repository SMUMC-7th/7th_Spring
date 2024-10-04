package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServicelmpl implements ReplyCommandService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public ReplyResDto.CreateReplyResponseDto createReply(ReplyReqDto.CreateReplyRequestDto requestDto) {
        //댓글 달 게시글 조회 -> 없으면 예외 처리
        Article article = articleRepository.findById(requestDto.articleId())
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));

        //DTO을 Entity로 변환해서 reply 테이블에 저장
        Reply savedReply = replyRepository.save(ReplyConverter.toEntity(requestDto, article));

        //저장 된 Entity를 reply로 변환 후 controller 단에 반환
        return ReplyConverter.from(savedReply);
    }
}
