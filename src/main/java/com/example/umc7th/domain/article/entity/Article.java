package com.example.umc7th.domain.article.entity;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private Integer likeNum;
    //createdAt, updatedAt 공통으로 묶어서 구현 ->BaseEntity
//    @CreatedDate
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    //article과 reply 일대다 매핑 추가
    @OneToMany(mappedBy = "article")
    private List<Reply> replies;
}

