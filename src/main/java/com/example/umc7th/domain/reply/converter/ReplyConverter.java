package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
import com.example.umc7th.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyConverter {

    // DTO -> Entity
    public static Reply toEntity(ReplyReqDto.CreateReplyRequestDto requestDto, Article article) {
        return Reply.builder()
                .article(article)
                .content(requestDto.content())
                .build();
    }

    // Entity -> DTO
    public static ReplyResDto.CreateReplyResponseDto from(Reply reply) {
        return ReplyResDto.CreateReplyResponseDto.builder()
                .id(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }
}
