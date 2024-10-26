package com.example.umc7th.entity;

import com.example.umc7th.dto.request.ReplyRequestDto;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "reply")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    public void update(ReplyRequestDto.UpdateReplyRequestDto dto){
        content = dto.getContent();
    }
}