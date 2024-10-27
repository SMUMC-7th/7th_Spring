package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getReplies();
    Page<Reply> getRepliesByArticleId(Long articleId, int pageNo, int pageSize);
}
