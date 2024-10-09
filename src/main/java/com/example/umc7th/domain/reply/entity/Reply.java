package com.example.umc7th.domain.reply.entity;

import com.example.umc7th.Time;
import com.example.umc7th.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 추가
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 인자 가지는 생성자 추가 (private으로 설정하여 객체 생성을 빌더 패턴으로 제한)
@Getter
@EntityListeners(value = AuditingEntityListener.class)
public class Reply extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // Reply와 Article은 다대일 관계
    @JoinColumn(name = "article_id")
    private Article article;


}
