package com.example.umc7th.domain.member.dto;

import com.example.umc7th.domain.member.entity.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class MemberPreviewDTO {

        private Long id;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;

        public static MemberPreviewDTO from(Member member) {
            return MemberPreviewDTO.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .createdAt(member.getCreatedAt())
                    .updateAt(member.getUpdatedAt())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class CreatedMemberResponseDTO {
        private String email;
        private String password;

        public static Member from(MemberRequestDTO.CreateMemberDTO dto, PasswordEncoder passwordEncoder) {

            return Member.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder
    public static class LoginMemberResponseDTO {

        private Long id;
        private String accessToken;
        private String refreshToken;

        public static LoginMemberResponseDTO from(Member member, String accessToken, String refreshToken) {

            return LoginMemberResponseDTO.builder()
                    .id(member.getId())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }
}
