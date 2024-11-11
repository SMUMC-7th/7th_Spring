package com.example.umc7th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberTokenDTO {
        String accessToken;
        String refreshToken;
    }
}
