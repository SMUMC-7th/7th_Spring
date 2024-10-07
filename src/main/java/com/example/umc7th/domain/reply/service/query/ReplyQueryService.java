package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {

    Reply getReply(Long replyId);
    List<Reply> getReplies(Long articleId);
}
