package com.example.umc7th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record MemberPreviewDTO(
            Long id,
            String email,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ){}

    @Builder
    public record LoginResDTO(
            Long id,
            String accessToken,
            String refreshToken
    ){}

    @Builder
    public record MemberTokenDTO (
        String accessToken,
        String refreshToken){ }
}
