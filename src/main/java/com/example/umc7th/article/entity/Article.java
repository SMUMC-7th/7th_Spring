package com.example.umc7th.article.entity;

import com.example.umc7th.reply.entity.Reply;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE) //필더 패턴으로 제한한다.
@Builder
@Getter
@Where(clause = "is_deleted = false")
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

    public void updateTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
    }

    public void updateContent(String content) {
        if (!content.isEmpty()) {
            this.content = content;
        }
    }

    public void addLikeNumber() {
        this.likeNum += 1;
    }

}
