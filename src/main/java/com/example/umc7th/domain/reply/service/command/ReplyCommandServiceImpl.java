package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    // 댓글 생성
    public Reply createReply(Long articleId, ReplyRequestDTO.CreateReplyDTO createReplyDTO) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        // 삭제된 게시글에는 댓글을 생성할 수 없음
        if (article.isDeleted()) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND);
        }

        Reply reply = ReplyConverter.toReply(createReplyDTO);
        reply.setArticle(article);
        replyRepository.save(reply);

        return reply;
    }

    @Override
    // 댓글 수정
    public void updateReply(Long replyId, Long articleId, ReplyRequestDTO.UpdateReplyDTO updateReplyDTO) {

        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND));
        // 삭제된 게시글의 댓글은 수정할 수 없음
        if (article.isDeleted()) {
            throw new ArticleException(ArticleErrorCode.ARTICLE_IS_DELETED);
        }

        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
        reply.update(updateReplyDTO.getContent());
    }

    @Override
    // 댓글 soft 삭제
    public void softDeleteReply(Long replyId) {

        Reply reply = replyRepository.findById(replyId).orElseThrow(() -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
        reply.setIsDeleted(true);
    }

}
