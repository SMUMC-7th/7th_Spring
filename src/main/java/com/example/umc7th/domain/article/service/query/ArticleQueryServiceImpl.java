package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.article.dto.ArticleResponseDTO;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.enums.ArticleSearchQuery;
import com.example.umc7th.domain.article.exception.ArticleErrorCode;
import com.example.umc7th.domain.article.exception.ArticleException;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;
    private final ReplyRepository replyRepository;

    //전체 게시글 조회
    @Override
    public List<ArticleResponseDTO.ArticlePreviewDTO> getArticleList(){
        List<Article> articles = articleRepository.findByActiveTrue();
        return ArticleConverter.fromList(articles);
    }

    //개별 게시글 조회
    @Override
    public ArticleResponseDTO.ArticlePreviewDTO getArticle(Long articleId){
        Article article = articleRepository.findByIdAndActiveTrue(articleId)
                .orElseThrow(() -> new ArticleException(ArticleErrorCode.NOT_FOUND));
        return ArticleConverter.from(article);
    }

    //댓글 있는 게시글 조회
    @Override
    public boolean hasReplies(Long articleId){
        return replyRepository.existsById(articleId);
    }

    //커서 기반 페이지네이션 조회
    @Override
    public Slice<Article> getArticlesByCursor(String query, Long cursor, Integer offset){
        Pageable pageable = PageRequest.of(0, offset);
        if(query.equals(ArticleSearchQuery.ID.name())){
            if(cursor.equals(0L)){
                return articleRepository.findAllByOrderByIdDesc(pageable);
            }
            return articleRepository.findAllByIdLessThanOrderByIdDesc(cursor,pageable);
        }
        else if (query.equals(ArticleSearchQuery.LIKE.name())) {
            if (cursor.equals(0L)){
                return articleRepository.findAllByOrderByLikeNumDescIdDesc(pageable);
            }
            return articleRepository.findAllByOrderByLikeWithCursor(cursor,pageable);
        }
        else {
            throw new ArticleException(ArticleErrorCode.UNSUPPORTED_QUERY);
        }
    }
}
