package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReplyQueryService {
    public Reply getReply(Long id);
    public Page<Reply> getReplies(Long articleId, Integer page, Integer offset);
}
