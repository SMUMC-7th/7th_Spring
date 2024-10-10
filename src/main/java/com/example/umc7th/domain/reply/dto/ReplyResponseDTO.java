package com.example.umc7th.domain.reply.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReplyResponseDTO {
    @Builder
    public record CreateReplyResponseDto(
            Long id,
            Long articleId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){
    }
}
