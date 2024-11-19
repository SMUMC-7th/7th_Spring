package com.example.umc7th.domain.reply.service.query;


import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.List;

public interface ReplyQueryService {
    List<Reply> getReplies();
    Reply getReply(Long id);
    //offset기반 특정 게시물 댓글 조회
    Page<Reply> getRepliesByArticleIdWithOffset(Long articleId, Long lastId, Pageable pageable);


}
