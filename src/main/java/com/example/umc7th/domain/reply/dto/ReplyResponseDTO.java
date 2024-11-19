package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.reply.entity.Reply;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        // Reply 리스트를 DTO 리스트로 변환하는 메서드
        public static ReplyPreviewListDTO fromReplies(List<Reply> replies) {
            List<ReplyPreviewDTO> replyDTOList = replies.stream()
                    .map(reply -> ReplyPreviewDTO.builder()
                            .id(reply.getId())
                            .content(reply.getContent())
                            .articleId(reply.getArticle().getId())
                            .createdAt(reply.getCreatedAt())
                            .updatedAt(reply.getUpdatedAt())
                            .build())
                    .collect(Collectors.toList());

            return ReplyPreviewListDTO.builder()
                    .replies(replyDTOList)
                    .build();
        }
    }
}





