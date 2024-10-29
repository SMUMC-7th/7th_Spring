package com.example.umc7th.reply.service.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
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
    @Override
    public List<Reply> getRepliesByArticleId(Long articleId, int page, int size) {
        Pageable pageable = (Pageable)PageRequest.of(page, size);
        return replyRepository.findRepliesByArticleOrderByCreatedAtDescNativeQuery(articleId);
    }

    @Override
    public List<Reply> getCursorBasedReplies(Long articleId, Long lastId, int size) {
        Pageable pageable = (Pageable)PageRequest.of(0, size); // 커서 기반이므로 페이지는 0으로 고정
        return replyRepository.findCursorBasedReplies(articleId, lastId, pageable);
    }
}