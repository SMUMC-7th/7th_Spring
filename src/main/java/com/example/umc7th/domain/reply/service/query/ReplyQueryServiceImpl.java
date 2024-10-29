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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public Reply getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND_404)
        );
        // 댓글이 삭제된 상태라면 에러 던짐
        if (!reply.isActivated()) {
            throw new ReplyException(ReplyErrorCode.REPLY_DELETED_410);
        }
        return reply;
    }

    @Override
    public List<Reply> getReplies() {
        List<Reply> Replies = replyRepository.findAll();
        // 삭제된 댓글 제거 후 반환
        return Replies.stream()
                .filter(Reply::isActivated)
                .toList();
    }

    @Override
    public Page<Reply> getRepliesForArticleOrderByCreatedAt(Long articleId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return replyRepository.findAllByArticleIdOrderByCreatedAtDesc(articleId, pageable);
    }
}
