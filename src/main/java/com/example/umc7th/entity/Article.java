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

    @Column(name = "likeNum")
    private int likeNum;

    @Column(name = "active")
    private boolean active;

//   단방향 매핑이 더 적합해 보여서 주석 처리
//    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Reply> replies;

    // 전체 수정
    public void update(ArticleRequestDto.UpdateArticleRequestDto dto){
        title = dto.getTitle();
        content = dto.getContent();
    }

    // 부분 수정
    public void updatePartial(ArticleRequestDto.PartialUpdateArticleRequestDto dto) {
        if (dto.getTitle() != null) {
            title = dto.getTitle();
        }
        if (dto.getContent() != null) {
            content = dto.getContent();
        }
    }

    public void softDelete() {
        this.active = false;
    }
}
