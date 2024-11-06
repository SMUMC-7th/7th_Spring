package com.example.umc7th.domain.reply.service.query;


import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
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

    @Override
    public Page<Reply> getRepliesByArticleIdWithOffset(Long articleId, Long lastId, Pageable pageable) {
        lastId = (lastId == null) ? Long.MAX_VALUE : lastId;  // 기본값 설정
        return replyRepository.findArticleIdByCreatedAtDesc(articleId, lastId, pageable);
    }
}
