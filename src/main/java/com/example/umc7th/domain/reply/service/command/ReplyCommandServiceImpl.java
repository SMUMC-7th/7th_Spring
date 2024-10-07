package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService{
    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO requestDTO){
        Article article = articleRepository.findById(requestDTO.articleId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Article"));

        return replyRepository.save(
                Reply.builder()
                        .content(requestDTO.content())
                        .article(article)
                        .build()
        );
    }
}
