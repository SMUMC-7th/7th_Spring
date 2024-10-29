package com.example.umc7th.reply.service.query;

import java.util.List;
import com.example.umc7th.reply.entity.Reply;

public interface ReplyQueryService {
    Reply getReply(Long id);
    List<Reply> getReplies();
    List<Reply> getRepliesByArticleId(Long articleId, int page, int size); // Offset 기반 페이지네이션
    List<Reply> getCursorBasedReplies(Long articleId, Long lastId, int size); // Cursor 기반 페이지네이션
}