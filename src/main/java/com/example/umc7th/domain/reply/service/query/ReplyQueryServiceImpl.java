package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;

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