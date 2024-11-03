package com.example.umc7th.reply.service.query;

import com.example.umc7th.article.error.ArticleCustomException;
import com.example.umc7th.article.error.ArticleErrorCode;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new ArticleCustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404);
        }
        return replyRepository.findAllByArticleId(articleId);
    }

    @Override
    public boolean isExistReplies(Long articleId) {
        if (!articleRepository.existsById(articleId)) {
            throw new ArticleCustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404);
        }
        return replyRepository.existsByArticleId(articleId);
    }
}
