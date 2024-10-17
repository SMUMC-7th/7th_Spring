package com.example.umc7th.domain.reply.dto.request;

public class ReplyReqDto {

    public record CreateReplyRequestDto(
            Long articleId,
            String content
    ) {
    }

    public record UpdateReplyRequestDto(
            String content
    ) {
    }
}
