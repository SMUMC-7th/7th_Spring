package com.example.umc7th.reply.service.query;

import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public List<Reply> getReplies(Long articleId) {
        return replyRepository.findByArticleId(articleId);
    }
}