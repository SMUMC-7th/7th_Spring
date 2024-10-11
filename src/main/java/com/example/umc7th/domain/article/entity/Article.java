package com.example.umc7th.domain.article.entity;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(name = "like_num")
    private int likeNum;

    @OneToMany(mappedBy = "article")
    private List<Reply> replies;
}
