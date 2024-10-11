package com.example.umc7th.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

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

    @Getter
    @Builder
    // 여러 Reply 검색의 응답
    public static class ReplyViewListDTO {
        private List<ReplyViewDTO> replyViewDTOs;
    }

}
