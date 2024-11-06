package com.example.umc7th.domain.reply.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReplyResponseDTO {
    @Builder
    public record ReplyPreviewDto(
            Long id,
            Long articleId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            boolean active
    ){
    }

    @Builder
    public record ReplyPagePreviewDto(
            List<ReplyPreviewDto> replies,
            int numOfRows,
            int pageNo,
            long totalCount
    ){}
}
