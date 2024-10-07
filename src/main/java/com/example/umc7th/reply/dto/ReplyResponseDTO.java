package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
import lombok.Getter;

@Getter
public class ReplyResponseDTO {
    private Long id;
    private String content;
    public ReplyResponseDTO(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
    }
}