package com.example.umc7th.reply.converter;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;

import java.util.List;

public class ReplyConverter {

    public static Reply toEntity(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();
    }

    public static ReplyResponseDTO toReplyResponseDto(Reply reply) {
        return ReplyResponseDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    public static List<ReplyResponseDTO> toReplyResponseDtoList(List<Reply> replyList) {
        return replyList.stream()
                .map(ReplyConverter::toReplyResponseDto)
                .toList();
    }
}
