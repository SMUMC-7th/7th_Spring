package com.example.umc7th.member.dto;

import com.example.umc7th.member.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

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
