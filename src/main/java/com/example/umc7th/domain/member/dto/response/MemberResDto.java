package com.example.umc7th.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDto {

    @Builder
    public record MemberPreviewDto(
            Long id,
            String email,
            String role,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record LoginResponseDto(
            Long id,
            String accessToken,
            String refreshToken
    ) {
    }

    @Builder
    public record MemberTokenDto(
            String accessToken,
            String refreshToken
    ) {
    }
}
