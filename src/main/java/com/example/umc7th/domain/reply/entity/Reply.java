package com.example.umc7th.domain.reply.entity;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;
    //createdAt, updatedAt 공통으로 묶어서 구현 ->BaseEntity
//    @CreatedDate
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    //댓글 수정
    public void update(ReplyRequestDTO.UpdateReplyDTO dto){
        content = dto.content();
    }
    //댓글 삭제

    @Column(name = "active")
    @Builder.Default() // builder 패턴 사용 시 값을 지정하지 않으면 기본 값으로 true를 넣는다.
    private boolean active = true;

    //댓글 softDelete()
    public void softDelete(){
        this.active = false;
    }

}
