package com.example.umc7th.domain.reply.dto;

import lombok.Getter;


public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private String content;
        private Long articleId;
    }
}
