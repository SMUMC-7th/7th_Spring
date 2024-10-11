package com.example.umc7th.reply.service.query;

import java.util.List;
import com.example.umc7th.reply.entity.Reply;

public interface ReplyQueryService {
    List<Reply> getReplies();
    Reply getReply(Long id);
}