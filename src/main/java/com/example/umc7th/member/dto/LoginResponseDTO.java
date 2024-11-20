package com.example.umc7th.member.dto;

public record LoginResponseDTO(
        String accessToken,
//        long accessExpiresIn,
        String refreshToken
//        long refreshExpiresIn
) {

}
