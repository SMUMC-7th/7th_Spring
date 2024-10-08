package com.example.umc7th.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;

public class ReplyResponseDTO {

    @Getter
    @Builder
    // Reply 생성의 응답
    public static class CreateReplyResultDTO {
        private Long id;
        private String content;
        private Long articleId;
    }

    @Getter
    @Builder
    // Reply 검색의 응답
    public static class ReplyViewDTO {
        private Long id;
        private String content;
        private Long articleId;
    }
}
