package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.domain.reply.entity.Reply;
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
    public List<Reply> getReplies() {
        return replyRepository.findAll();
    }

    @Override
    public Reply getReply(Long id) {
        return replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
    }

    @Override
    public Page<Reply> getRepliesByArticleId(Long articleId, int pageNumber, int pageSize) {

//        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("articleId").descending()); //

        // pageNumber와 pageSize 값을 기준으로 Pageable 객체 생성
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return replyRepository.findAllByArticleIdOrderByCreatedAtDesc(articleId, pageable);
    }
}
