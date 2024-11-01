package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

// reply 조회를 위한 쿼리 서비스 인터페이스
public interface ReplyQueryService {
    List<Reply> getReplies();
    Reply getReply(Long id);
    Page<Reply> getRepliesWithPagination(int page, int size);
}