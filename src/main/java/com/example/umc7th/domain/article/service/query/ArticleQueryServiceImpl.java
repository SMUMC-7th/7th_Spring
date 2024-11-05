package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.controller.ArticleSearchCond;
import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> getArticles(String keyword) {
        if (keyword == null) {
            return getArticles();
        }
        return articleRepository.findByTitleContaining(keyword);
    }

    @Override
    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new ArticleException(ArticleErrorCode.ARTICLE_NOT_FOUND_404));
    }

    @Override
    public boolean hasComments(Long id) {
        return replyRepository.existsByArticleId(id);
    }

    @Override
    public ArticleResponseDTO.ArticleCursorPreviewListDTO getArticlesOrderBy(ArticleSearchCond sortCond, String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);

        Slice<Article> articles;
        switch (sortCond) {
            case CREATED_AT:
                articles = articleRepository.findAllByCreatedAtLessThanOrderByCreatedAtDesc(
                        (cursor == null) ? LocalDateTime.now() : LocalDateTime.parse(cursor),
                        pageable);
                break;
            case LIKE_NUM:
                articles = articleRepository.findByLikeNumCursor(
                        (cursor == null) ? "99999999999999999999" : cursor,
                        pageable);
                break;
            default:
                articles = articleRepository.findAllByIdLessThanOrderByIdDesc(
                        (cursor == null) ? Long.MAX_VALUE : Long.parseLong(cursor),
                        pageable);
        }

        String nextCursor = getCursor(sortCond, articles);

        return ArticleConverter.toArticleCursorPreviewListDTO(articles, nextCursor);
    }

    private String getCursor(ArticleSearchCond sortCond, Slice<Article> articles) {
        String cursor;
        List<Article> content = articles.getContent();

        switch (sortCond) {
            case CREATED_AT:
                cursor = content.get(content.size() - 1).getCreatedAt().toString();
                break;
            case LIKE_NUM:
                Article lastArticle = content.get(content.size() - 1);
                cursor = String.format("%010d%010d", lastArticle.getLikeNum(), lastArticle.getId());
                break;
            default:
                cursor = content.get(content.size() - 1).getId().toString();
                break;
        }

        return cursor;
    }
}