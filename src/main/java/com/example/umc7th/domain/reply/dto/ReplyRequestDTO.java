package com.example.umc7th.domain.reply.dto;

import lombok.Getter;

public class ReplyRequestDTO {
    public record CreateReplyDTO(
            Long articleId,
            String content){
    }
}
