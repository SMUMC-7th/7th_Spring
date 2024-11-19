package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticle(Long id) {
        //get(): 값이 없으면 NoSuchElementException이 발생할 수 있음 -> orElseThrow사용
        return articleRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("게시물 없음")); //ArticleErrorCode 이용가능
    }
    @Override
    public boolean hasReply(Long id){
        return articleRepository.existsByArticleId(id);
    }

    @Override
    public Slice<Article> getArticlesAfterCursorById(Long cursorId, Pageable pageable) {
        return articleRepository.findAllByIdGreaterThan(cursorId, pageable);
    }

}
