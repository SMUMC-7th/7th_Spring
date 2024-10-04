package com.example.umc7th.reply.dto;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.entity.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class AddReplyRequestDTO {

    private Long id;
    private String content;
    private Long article_id;

    @Builder
    public AddReplyRequestDTO(Long id, String content, Long article_id) {
        this.id = id;
        this.content = content;
        this.article_id = article_id;
    }

    public Reply toEntity(Article article) { // 댓글 만들 때 연관관계 있는 article객체를 파라미터로 받도록
        return Reply.builder()
                .id(id)
                .content(content)
                .article(article)
                .build();
    }
}
