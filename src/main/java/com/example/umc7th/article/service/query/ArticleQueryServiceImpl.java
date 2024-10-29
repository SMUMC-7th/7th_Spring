package com.example.umc7th.article.service.query;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.exception.ArticleErrorCode;
import com.example.umc7th.article.exception.ArticleException;
import com.example.umc7th.article.exception.ArticleNotFoundException;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<Article> getArticles() {
				// 구현, 힌트: findAll()
        return articleRepository.findAll(); // 모든 게시글 조회
    }

    @Override
    public Article getArticle(Long id) {
				// 구현, 힌트: findById(Long id) 
				// findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여 ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요

    return articleRepository.findById(id).orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
    }
    @Override
    public boolean hasReplies(Long articleId) {
        return replyRepository.existsByArticleId(articleId);
    }

    // 댓글 오프셋 기반 페이지네이션
    @Override
    public List<Reply> getRepliesWithOffsetPagination(Long articleId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return replyRepository.findRepliesByArticleOrderByCreatedAtDescNativeQuery(articleId);
    }

    // 댓글 커서 기반 페이지네이션
    @Override
    public List<Reply> getRepliesWithCursorPagination(Long articleId, Long lastReplyId, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return replyRepository.findCursorBasedReplies(articleId, lastReplyId, pageable);
    }

    // 게시글 커서 페이지네이션 (ID 기준)
    @Override
    public Slice<Article> getArticlesByIdCursor(Long cursorId, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return articleRepository.findAllByIdLessThanOrderByIdDesc(cursorId, pageable);
    }

    // 게시글 커서 페이지네이션 (생성 날짜 기준)
    @Override
    public Slice<Article> getArticlesByDateCursor(LocalDateTime cursorDate, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return articleRepository.findAllByCreatedAtBeforeOrderByCreatedAtDesc(cursorDate, pageable);
    }

    // 게시글 커서 페이지네이션 (좋아요 수 기준)
    @Override
    public Slice<Article> getArticlesByLikesCursor(Integer likesCount, Long id, int size) {
        Pageable pageable = PageRequest.of(0, size);
        return articleRepository.findAllByLikesAndIdLessThanOrderByLikesCountDesc(likesCount, id, pageable);
    }
}