package com.example.umc7th.article.service.query;

import com.example.umc7th.article.dto.DetailArticleResponseDTO;
import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.error.ArticleCustomException;
import com.example.umc7th.article.error.ArticleErrorCode;
import com.example.umc7th.article.repository.ArticleRepository;
import com.example.umc7th.article.repository.QArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
//조회는 Query
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final ArticleRepository articleRepository;
    private final QArticleRepository qArticleRepository;

    @Override
    public Article getDetailArticle(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new ArticleCustomException(ArticleErrorCode.ARTICLE_NOT_FOUND_404));
    }

    @Override
    public Slice<DetailArticleResponseDTO> getArticles(Long cursorId, int size, String likeTitle, boolean isLikedSort) {
        Slice<Article> sliceArticle = qArticleRepository.search(cursorId, size, likeTitle, isLikedSort);
        List<DetailArticleResponseDTO> dto = sliceArticle.stream()
                .map(DetailArticleResponseDTO::from)
                .toList();
        return new SliceImpl<>(dto, sliceArticle.getPageable(), sliceArticle.hasNext());
    }
}
