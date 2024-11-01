package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
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

    //offset 페이지네이션을 위해 댓글 내용의 List를 받는 responseDTO
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReplyPagePreviewListDTO {
        private List<ReplyPreviewDTO> replies;
        private int numOfRows;
        private int pageNo;
        private long totalCount;

        public static ReplyPagePreviewListDTO from(Page<Reply> replyPage) {
            return ReplyPagePreviewListDTO.builder()
                    .replies(replyPage.getContent().stream().map(ReplyPreviewDTO::from).toList())
                    .numOfRows(replyPage.getSize()) //페이지네이션에 설정된 페이지 크기(한 페이지에 몇개의 요소 표시?)
                    .pageNo(replyPage.getNumber()) // 현재 페이지의 인덱스 (몇 번째 페이지인지, 0부터 시작)
                    .totalCount(replyPage.getTotalElements()) // 전체 요소(여기서는 댓글)의 수 반환
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
