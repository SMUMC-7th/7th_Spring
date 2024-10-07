package com.example.umc7th.domain.article.entity;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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

    @Column
    private String title;
    @Column
    private String content;


    public static Article toArticle(ArticleRequestDTO.CreateArticleDTO dto){
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
    }
}
