package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.exception.ReplyErrorCode;
import com.example.umc7th.domain.reply.exception.ReplyException;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;


    // 댓글을 조회하고, 없으면 예외를 발생
    @Override
    public Reply getReply(Long id) {
        return replyRepository.findById(id).orElseThrow(() ->
                new ReplyException(ReplyErrorCode.NOT_FOUND));
    }

    // 특정 게시글에 속한 댓글을 페이징하여 조회
    @Override
    public Page<Reply> getReplies(Long articleId, Integer page, Integer offset) {

        // 게시글이 존재하는지 확인
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.NOT_FOUND));

        // 페이징 설정을 위한 Pageable 객체 생성
        Pageable pageable = PageRequest.of(page - 1, offset);
        // 특정 게시글에 대한 댓글 목록을 페이지 단위로 조회
        return replyRepository.findAllByArticleIsOrderByCreatedAtDesc(article, pageable);
    }
}