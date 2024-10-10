package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import java.util.List;

public interface ReplyQueryService {
    public Reply getReply(Long id);
    public List<Reply> getReplies();
}