package com.example.umc7th.domain.reply.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResDto {

    @Builder
    public record CreateReplyResponseDto(
            Long id,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record ReplyPreviewDto(
            Long id,
            Long articleId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record ReplyPreviewListDto(
            List<ReplyPreviewDto> replies,
            int numOfRows,
            int pageNo,
            long totalCount
    ) {
    }
}
