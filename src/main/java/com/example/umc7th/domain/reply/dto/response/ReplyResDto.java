package com.example.umc7th.domain.reply.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReplyResDto {

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
