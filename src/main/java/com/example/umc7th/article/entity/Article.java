package com.example.umc7th.article.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) //필더 패턴으로 제한한다.
@Builder
@Getter
public class Article extends BaseTimeEntity {
    @OneToMany(mappedBy = "article") //외래키를 관리하는 주인은 reply이다. article에 매핑된다.
    @Builder.Default
    List<Reply> replies = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int likeNum;
}