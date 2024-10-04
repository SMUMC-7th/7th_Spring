package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
import com.example.umc7th.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    // Entity 리스트 -> DTO 리스트 변환
    public static List<ReplyResDto.CreateReplyResponseDto> fromList(List<Reply> replies) {
        return replies.stream()
                .map(ReplyConverter::from)
                .collect(Collectors.toList());
    }
}
