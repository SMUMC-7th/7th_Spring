package com.example.umc7th.domain.article.entity;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "article")
@Getter
//다른 곳에서는 사용하지 못하지만 jpa가 엔티티를 조회하거나 영속성컨텍스트 관리할때 사용
@NoArgsConstructor(access= AccessLevel.PROTECTED)
//수정할 수 없는 builder패턴만 사용할 수 있게하여 불변성방지
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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

    public void updateArticle(ArticleRequestDTO.UpdateArticleDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
