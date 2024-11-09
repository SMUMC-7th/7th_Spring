package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        // 주어진 ID로 댓글을 조회하고 없으면 예외 발생
        return replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
    }

    @Override
    // 모든 댓글을 조회하여 반환
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    @Override
    public Page<Reply> getRepliesWithPagination(int page, int size) {
        // 페이지 요청을 생성하고, 댓글을 페이지로 조회하여 반환
        Pageable pageable = PageRequest.of(page, size);
        return replyRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
}