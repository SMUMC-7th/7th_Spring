package com.example.umc7th.domain.article.service.query;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.article.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return articleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("게시물 없음"));
        // findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여 ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요
    }
}
