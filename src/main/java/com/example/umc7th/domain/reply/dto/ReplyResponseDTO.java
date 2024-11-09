package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.reply.entity.Reply;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
    public static class ReplyPreviewListDTO {
        private List<ReplyPreviewDTO> replies;
        //DTO에 page 정보 추가
        private int numOfRows;
        private int pageNo;
        private int totalCount;
    }

    @Getter
    @AllArgsConstructor
    public static class DeleteReplyResponseDTO {
        private Long id;
        private String message;
    }
}