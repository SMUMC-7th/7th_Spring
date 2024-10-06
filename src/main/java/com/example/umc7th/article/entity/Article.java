package com.example.umc7th.article.entity;

import com.example.umc7th.global.BaseTimeEntity;
import com.example.umc7th.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private int likeNum;

    //OneToMany -> Article은 여러개의 Reply를 가질 수 있다
    //mappedBy = "article" -> reply 엔티티에서 관계가 article 필드를 통해 이어졌다
    //cascade = CascadeType.ALL -> Article이 삭제되면 이에 해당하는 모든 Reply도 삭제
    //orphanRemoval = true -> Article에서 Reply목록이 제거되면 남아있는 Reply도 자동 삭제됨
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList;
}
