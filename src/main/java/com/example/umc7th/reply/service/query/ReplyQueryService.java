package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
//조회는 Query
public interface ReplyQueryService {

    List<Reply> getReplies(Long articleId);

    boolean isExistReplies(Long articleId);
}
