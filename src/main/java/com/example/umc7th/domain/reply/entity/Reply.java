package com.example.umc7th.domain.reply.entity;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "reply")
@Where(clause = "is_deleted = false")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public void softDelete() {
        this.isDeleted = true;
    }

    public void update(String content) {
        this.content = content;
    }
}
