package com.example.umc7th.reply.service.query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.exception.ReplyErrorCode;
import com.example.umc7th.reply.exception.ReplyException;
import com.example.umc7th.reply.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService {

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