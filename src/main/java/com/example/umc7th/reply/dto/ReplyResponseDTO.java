package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
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

        public static CreateReplyResponseDTO from(Reply reply) {
            return CreateReplyResponseDTO.builder()
                    .id(reply.getId())
                    .createdAt(reply.getCreatedAt())
                    .build();
        }
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

        public static  ReplyPreviewDTO from(Reply reply) {
            return ReplyPreviewDTO.builder()
                    .id(reply.getId())
                    .content(reply.getContent())
                    .articleId(reply.getArticle().getId())
                    .createdAt(reply.getCreatedAt())
                    .updatedAt(reply.getUpdatedAt())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPreviewListDTO {
        private List<ReplyPreviewDTO> replies;

        public static ReplyPreviewListDTO from(List<Reply> replies) {
            return ReplyPreviewListDTO.builder()
                    .replies(replies.stream().map(ReplyPreviewDTO::from).toList())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateReplyResponseDTO {
        private Long id;
        private LocalDateTime updatedAt;

        public static UpdateReplyResponseDTO from(Reply reply) {
            return UpdateReplyResponseDTO.builder()
                    .id(reply.getId())
                    .updatedAt(reply.getUpdatedAt())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DeleteReplyResponseDTO {
        private Long id;

        public static DeleteReplyResponseDTO from(Reply reply) {
            return DeleteReplyResponseDTO.builder()
                    .id(reply.getId())
                    .build();
        }
    }
}
