package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyResponseDTO {
    private Long id;
    private String content;
    private Long articleId;

    public static ReplyResponseDTO ToDTO(Reply reply){
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .articleId(reply.getArticle().getId())
                .build();
    }
}
