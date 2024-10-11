package com.example.umc7th.reply.service.query;

import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.exception.ReplyErrorCode;
import com.example.umc7th.reply.exception.ReplyException;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;
    private final ArticleRepository articleRepository;


    @Override
    public Reply getReply(Long id) {
        return replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
    }

    @Override
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }
}
