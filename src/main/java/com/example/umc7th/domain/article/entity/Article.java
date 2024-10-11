package com.example.umc7th.domain.article.entity;

import com.example.umc7th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "like_num")
    private int likeNum;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLikeNum() {
        likeNum++;
    }

}
