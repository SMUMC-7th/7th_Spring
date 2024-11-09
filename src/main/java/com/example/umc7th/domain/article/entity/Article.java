package com.example.umc7th.domain.article.entity;

import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private int likeNum;

    @OneToMany(mappedBy = "article")
    private List<Reply> replies;

    /*
    Dirty Checking은 JPA의 **영속성 컨텍스트(Persistence Context)**가 관리하는 엔티티 객체에서 변경 사항이 발생했는지를 자동으로 감지하여,
    트랜잭션이 종료될 때 변경된 필드를 데이터베이스에 자동으로 업데이트하는 기능

    JPA는 트랜잭션 내에서 변경 전의 상태(Snapshot)와 현재 상태를 비교하여 변경 사항을 감지
    트랜잭션이 커밋되는 시점에, 변경된 필드가 있다면 UPDATE SQL문을 자동으로 생성하여 변경 사항을 데이터베이스에 반영
    이 과정에서 개발자가 명시적으로 save 메서드를 호출하지 않아도 Dirty Checking을 통해 자동으로 업데이트

    Dirty Checking은 영속성 컨텍스트에서 관리되는 엔티티 객체에 적용되는 기능
    엔티티는 영속성 컨텍스트가 그 상태를 추적하고, 변경 사항을 감지하여 데이터베이스와 동기화할 수 있도록 돕는 중심이 되므로,
    Dirty Checking을 활용하려면 엔티티에 데이터를 넣고 변경을 해야 한다!
     */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void increaseLike() {
        this.likeNum++;
    }
}