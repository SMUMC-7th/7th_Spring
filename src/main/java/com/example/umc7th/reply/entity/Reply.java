package com.example.umc7th.reply.entity;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//모든 인자를 가지는 생성자 추가 (private으로 선언하여 객체 생성을 Builder생성 제한)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reply extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column의 이름을 지정하기 위해 사용
    @Column(name = "content")
    private String content;

    // N:1 매핑, fetchType을 LAZY로 변경 (default = EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    // 해당 article을 article_id라는 이름으로 Column 추가 (실제 객체가 아닌 Long id를 저장하기에 이름을 article_id로 지정)
    @JoinColumn(name = "article_id")
    private Article article;
}
