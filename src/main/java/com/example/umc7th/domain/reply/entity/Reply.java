package com.example.umc7th.domain.reply.entity;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    // dirty checking
    // 엔티티 내부에 상태 변경 메서드를 두면 JPA가 변경을 자동으로 감지할 수 있음
    public void update(String content) {
        this.content = content;
    }

}
