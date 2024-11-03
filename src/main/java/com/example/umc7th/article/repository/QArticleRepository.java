package com.example.umc7th.article.repository;

import static com.example.umc7th.article.entity.QArticle.article;

import com.example.umc7th.article.entity.Article;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QArticleRepository implements ArticleRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<Article> search(
            Long cursorId,
            int size,
            String likeTitle,
            boolean isLikedSort) {
        List<Article> articles = getArticleJPAQuery(cursorId, likeTitle, isLikedSort)
                .limit(size).fetch();
        boolean hasNext = false;
        if (!articles.isEmpty()) {
            Long lastId = articles.get(articles.size() - 1).getId();
            hasNext = isExistByIdLessThan(lastId, likeTitle, isLikedSort);
        }
        return new SliceImpl<>(articles, Pageable.ofSize(size), hasNext);
    }

    private JPAQuery<Article> getArticleJPAQuery(Long cursorId, String likeTitle, boolean isLikedSort) {
        return queryFactory
                .selectFrom(article)
                .where(
                        titleContains(likeTitle),
                        lessThanCursorId(cursorId)
                )
                .orderBy(
                        isLikedSort ? article.likeNum.desc() : article.likeNum.asc(),
                        article.createdAt.desc());
    }

    //헬퍼 메서드
    private BooleanExpression titleContains(String likeTitle) {
        //null 이 들어가는 경우 해당 조건이 생략된다.
        return likeTitle != null ? article.title.contains(likeTitle) : null;
    }

    private BooleanExpression lessThanCursorId(Long cursorId) {
        return cursorId != null ? article.id.lt(cursorId) : null;
    }

    private boolean isExistByIdLessThan(Long lastId, String title, boolean isLikedSort) {
        return getArticleJPAQuery(lastId, title, isLikedSort).fetchFirst() != null;
    }

}
