package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReplyQueryService {
    Page<Reply> getReplies(Long articleId, Integer page, Integer offset);
    Reply getReply(Long id);
}