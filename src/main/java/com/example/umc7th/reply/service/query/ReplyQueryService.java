package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyQueryService {
    Optional<Reply> getReply(Long replyId);
    List<Reply> getRepliesByArticleId(Long articleId);
}