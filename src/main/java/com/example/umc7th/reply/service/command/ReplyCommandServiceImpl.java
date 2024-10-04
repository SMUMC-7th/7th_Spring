package com.example.umc7th.reply.service.command;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.dto.AddReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;


    @Override
    public Reply createReply(AddReplyRequestDTO dto, Article article) {

        Reply reply = dto.toEntity(article);
        return replyRepository.save(reply);
    }
}
