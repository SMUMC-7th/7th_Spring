package com.example.umc7th.entity;


import com.example.umc7th.dto.request.ArticleRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "article")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Article extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "like_num")
    private int likeNum;

    @Column(name = "active")
    private boolean active;

//   단방향 매핑이 더 적합해 보여서 주석 처리
//    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Reply> replies;

    // 전체 수정
    public void update(String title, String content){
       this. title = title;
        this.content = content;
    }

    // 부분 수정
    public void updatePartial(String title, String content) {
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
    }

    public void softDelete() {
        this.active = false;
    }

    public void increaseLikeNum(){
        likeNum++;
    }
}
