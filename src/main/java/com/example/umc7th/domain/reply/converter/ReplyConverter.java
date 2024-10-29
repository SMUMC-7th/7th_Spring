package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

import java.util.List;

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

    public static ReplyResponseDTO.ReplyViewListDTO toReplyViewListDTO(List<Reply> replies) {

        List<ReplyResponseDTO.ReplyViewDTO> replyViewDTOs = replies.stream()
                .map(ReplyConverter::toReplyViewDTO)
                .toList();

        return ReplyResponseDTO.ReplyViewListDTO.builder()
                .replyViewDTOs(replyViewDTOs)
                .build();
    }

}
