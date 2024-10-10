package com.example.umc7th.reply.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ReplyRequestDTO {
    @Data
    @Getter
    public static class CreateReplyDTO {
        private final Long articleId;
        private final String title;
        private final String content;

    }
}
