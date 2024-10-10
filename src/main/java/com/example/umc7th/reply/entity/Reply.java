package com.example.umc7th.reply.entity;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.article.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) //필더 패턴으로 제한한다.
@Builder
@Getter
@Where(clause = "is_deleted = false")
public class Reply extends BaseTimeEntity {
    @Id
    // PK의 생성 전략 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void update(String content) {
        this.content = content;
    }
}
