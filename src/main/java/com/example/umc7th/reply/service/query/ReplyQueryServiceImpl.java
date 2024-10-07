package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
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
    public Optional<Reply> getReply(Long replyId) {
        return replyRepository.findById(replyId);
    }

    @Override
    public List<Reply> getRepliesByArticleId(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }
}