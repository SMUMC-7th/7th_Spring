package com.example.umc7th.reply.service.query;

import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.controller.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.controller.global.apiPayload.exception.CustomException;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
//조회는 Query
public class ReplyQueryServiceImpl implements ReplyQueryService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<Reply> getReplies(Long articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new CustomException(GeneralErrorCode.ARTICLE_NOT_FOUND_404);
        }
        return replyRepository.findAllByArticleId(articleId);
    }

}
