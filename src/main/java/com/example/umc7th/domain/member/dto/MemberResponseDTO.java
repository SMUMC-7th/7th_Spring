package com.example.umc7th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class CreateMemberResultDTO {
        private Long memberId;
        private String email;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    public static class LoginResponseDTO {
        private Long memberId;
        private String accessToken;
        private String refreshToken;
    }

    @Getter
    @Builder
    public static class MemberViewDTO {
        private Long memberId;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Builder
    public static class MemberTokenDTO {
        private String accessToken;
        private String refreshToken;
    }

}
