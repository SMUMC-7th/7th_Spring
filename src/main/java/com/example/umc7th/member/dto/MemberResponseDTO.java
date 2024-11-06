package com.example.umc7th.member.dto;

import com.example.umc7th.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpResponse {
        private Long id;
        private String email;
        private LocalDateTime createdAt;

        public static SignUpResponse from(Member member) {
            return SignUpResponse.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .createdAt(member.getCreatedAt())
                    .build();
        }
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LoginResponse {
        private Long id;
        private String email;

        public static LoginResponse from(Member member) {
            return LoginResponse.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .build();
        }
    }

}
