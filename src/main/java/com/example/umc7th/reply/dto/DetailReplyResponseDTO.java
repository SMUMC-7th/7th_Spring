package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
import lombok.Data;

@Data
public class DetailReplyResponseDTO {
    private Long id;
    private String content;

    public DetailReplyResponseDTO(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
    }
}
