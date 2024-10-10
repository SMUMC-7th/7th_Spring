package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//조회는 Query
public interface ReplyQueryService {

    List<Reply> getReplies(Long articleId);
}
