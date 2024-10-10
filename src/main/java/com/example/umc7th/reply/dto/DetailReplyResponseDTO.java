package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailReplyResponseDTO {
    private Long id;
    private String content;

    public static DetailReplyResponseDTO of(Reply reply) {
        return DetailReplyResponseDTO.builder()
                .id(reply.getId()).content(reply.getContent())
                .build();
    }
}
