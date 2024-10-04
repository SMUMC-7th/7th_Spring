package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;

    @Override
    public List<Reply> getReplies(Long articleId) {

        return replyRepository.findRepliesByArticleId(articleId);
    }
}
