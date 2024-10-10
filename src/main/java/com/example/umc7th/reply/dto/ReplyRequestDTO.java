package com.example.umc7th.reply.dto;

import lombok.Data;

@Data
public class ReplyRequestDTO {
    //record(getter 기본제공, 불변객체 제공)
    public record CreateReplyDTO(String content) {
    }

    public record UpdateReplyDTO(String content) {
    }
}
