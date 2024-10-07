package com.example.umc7th.article.service.query;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.exception.ArticleNotFoundException;
import com.example.umc7th.article.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
// Query는 읽기만 하니 ReadOnly로 작성
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryServiceImpl implements ArticleQueryService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> getArticles() {
				// 구현, 힌트: findAll()
        return articleRepository.findAll(); // 모든 게시글 조회
    }

    @Override
    public Article getArticle(Long id) {
				// 구현, 힌트: findById(Long id) 
				// findById의 결과로 Optional 형태가 나올 예정인데 1주차 워크북의 구현된 Error code를 참고하여 ArticleErrorCode를 작성해보시고 직접 에러를 발생시키셔도 좋고 아니면 일단 .get()을 사용하시고 제가 세미나에서 알려드릴게요

    return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException("게시물이 보이지 않습니다"));
    }
}