package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.domain.Page;

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

        public static ReplyResponseDTO.CreateReplyResponseDTO from(Reply reply) {
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

        public static ReplyResponseDTO.ReplyPreviewDTO from(Reply reply) {
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

        public static ReplyResponseDTO.ReplyPreviewListDTO from(List<Reply> replies) {
            return ReplyPreviewListDTO.builder()
                    .replies(replies.stream().map(ReplyPreviewDTO::from).toList())
                    .build();
        }
    }
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPreviewPageDTO {
        private List<ReplyPreviewDTO> replies;
        private int numOfRows;
        private int pageNo;
        private long totalCount;

        public static ReplyResponseDTO.ReplyPreviewPageDTO from(Page<Reply> replies) {
            return ReplyPreviewPageDTO.builder()
                    .replies(replies.getContent().stream().map(ReplyPreviewDTO::from).toList())
                    .numOfRows(replies.getSize())
                    .pageNo(replies.getNumber())
                    .totalCount(replies.getTotalElements())
                    .build();
        }
    }
}
