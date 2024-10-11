package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.converter.ArticleConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReplyConverter {

    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO createReplyDTO) {

        return Reply.builder()
                .content(createReplyDTO.getContent())
                .build();
    }

    public static ReplyResponseDTO.CreateReplyResultDTO toCreateReplyResultDTO(Reply reply) {

        return ReplyResponseDTO.CreateReplyResultDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .articleId(reply.getArticle().getId())
                .build();
    }

    public static ReplyResponseDTO.ReplyViewDTO toReplyViewDTO(Reply reply) {

        return ReplyResponseDTO.ReplyViewDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .articleId(reply.getArticle().getId())
                .build();
    }

    public static List<ReplyResponseDTO.ReplyViewDTO> toReplyViewListDTO(List<Reply> replies) {

        return replies.stream()
                .map(ReplyConverter::toReplyViewDTO)
                .collect(Collectors.toList());
    }

}
