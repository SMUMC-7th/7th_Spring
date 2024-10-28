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

    // CascadeType.ALL -> Article을 삭제하면 그와 연관된 모든 Reply도 자동으로 삭제
    // orphanRemoval = true 고아 객체 삭제
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateLikeNum() {
        this.likeNum++;
    }
}
