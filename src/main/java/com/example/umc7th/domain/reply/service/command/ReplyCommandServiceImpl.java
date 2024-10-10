package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
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
}