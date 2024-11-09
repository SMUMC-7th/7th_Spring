package com.example.umc7th.domain.reply.dto;

import lombok.Getter;

/** 댓글 요청을 위한 DTO */
public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {
        private String content;
        private Long articleId;
    }

    @Getter
    public static class UpdateReplyDTO {
        private String content;
    }
}