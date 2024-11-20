package com.example.umc7th.member.dto;

import lombok.Builder;

@Builder
public record LoginResponseDTO(
        String accessToken,
//        long accessExpiresIn,
        String refreshToken
//        long refreshExpiresIn
) {

}
