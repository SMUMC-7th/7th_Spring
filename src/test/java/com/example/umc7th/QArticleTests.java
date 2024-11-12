package com.example.umc7th;

import static com.example.umc7th.article.entity.QArticle.article;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.umc7th.article.entity.Article;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QArticleTests {

    @Autowired
    private EntityManager em;
    private JPAQueryFactory jpaQueryFactory;

    @BeforeEach
    void seUp() {
        this.jpaQueryFactory = new JPAQueryFactory(em);
        Article article1 = Article.builder()
                .title("title1")
                .content("content1")
                .build();
        Article article2 = Article.builder()
                .title("title2")
                .content("content2")
                .build();
        em.persist(article1);
        em.persist(article2);
    }

    @Test
    void 제목에_해당하는_Article을_찾는다() {
        Article findArticle = jpaQueryFactory
                .select(article)
                .from(article)
                .where(article.title.eq("title1"))
                .fetchOne();
        assertThat(findArticle.getTitle()).isEqualTo("title1");
    }

    @Test
    void 제목과_내용에_해당하는_Article을_찾는다() {
        Article findArticle = jpaQueryFactory
                .selectFrom(article)
                .where(article.title.eq("title1"),
                        (article.content.eq("content1")))
                .fetchOne();
        assertThat(findArticle.getTitle()).isEqualTo("title1");
        assertThat(findArticle.getContent()).isEqualTo("content1");
    }

    @Test
    void resultFetch() {
        //여러개를 가져옴
        List<Article> fetch = jpaQueryFactory
                .selectFrom(article)
                .fetch();
        //하나만 가져옴
        Article fetchOne = jpaQueryFactory
                .select(article)
                .fetchOne();
        //첫번째 거를 가져옴
        Article fetchFirst = jpaQueryFactory
                .select(article)
                .fetchFirst();
        QueryResults<Article> results = jpaQueryFactory
                .selectFrom(article)
                .fetchResults(); //뭔가 요즘 사용권장은 안하는듯

        results.getTotal(); //페이징할 때 어디까지 페이지가 있는지 알 수 있어서 사용한다.
        List<Article> content = results.getResults();

        long total = jpaQueryFactory
                .selectFrom(article)
                .fetchCount(); //count만 가져오는 아이

    }

    @Test
    void 최신순으로_정렬한다() {
        List<Article> articles = jpaQueryFactory
                .selectFrom(article)
                .orderBy(article.createdAt.desc().nullsLast())
                .fetch();

        Article article1 = articles.get(0);
        Article article2 = articles.get(1);

        assertThat(article1.getContent()).isEqualTo("content2");
        assertThat(article2.getContent()).isEqualTo("content1");
    }
}
