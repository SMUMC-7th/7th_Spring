package com.example.umc7th.domain.reply.dto;

import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    // Reply 생성에 필요한 데이터
    public static class CreateReplyDTO {
        private String content;
    }
}
