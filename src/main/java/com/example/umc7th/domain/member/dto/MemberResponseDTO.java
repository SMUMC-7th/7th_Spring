package com.example.umc7th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 서버에서 클라이언트로 응답할 때 사용
public class MemberResponseDTO {

    // 토큰 정보 응답
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberTokenDTO {
        String accessToken;
        String refreshToken;
    }
}
