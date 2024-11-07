package com.example.umc7th.domain.member.converter;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(Member member) {
        return MemberResponseDTO.MemberInfoDTO.builder()
                .email(member.getEmail())
                .build();
    }

    public static MemberResponseDTO.MemberTokenDTO toMemberTokenDTO(String accessToken, String refreshToken) {
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
