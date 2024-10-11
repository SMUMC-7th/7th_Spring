package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getReplies();
}
