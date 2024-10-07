package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.global.apiPayload.code.CustomErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    @Override
    public Reply getReply(Long id) {
        Optional<Reply> reply = replyRepository.findById(id);
        return reply.orElseThrow(() -> new GeneralException(CustomErrorCode.REPLY_NOT_FOUND));
    }
}
