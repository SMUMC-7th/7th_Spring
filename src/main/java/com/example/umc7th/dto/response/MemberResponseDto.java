package com.example.umc7th.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberPreviewDto {
        public Long id;
        public String email;
        public String role;
        public LocalDateTime createdAt;
        public LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginResponseDto {
        public Long id;
        public String accessToken;
        public String refreshToken;
    }
}


