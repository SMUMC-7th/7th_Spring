package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.reply.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/** 댓글 응답을 위한 DTO */
public class ReplyResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateReplyResponseDTO {
        private Long id;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPreviewDTO {
        private Long id;
        private String content;
        private Long articleId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    // replies 목록과 페이지 정보를 포함
    public static class ReplyPreviewListDTO {
        private List<ReplyPreviewDTO> replies;
        private int size;
        private int pageNo;
        private int totalPage;
    }
}