package com.example.umc7th.domain.member.converter;

import com.example.umc7th.domain.member.dto.request.MemberReqDto;
import com.example.umc7th.domain.member.dto.response.MemberResDto;
import com.example.umc7th.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConverter {

    public static Member toMember(MemberReqDto.CreateMemberRequestDto dto, PasswordEncoder passwordEncoder) {
        String newPassword = passwordEncoder.encode(dto.password());
        return Member.builder()
                .email(dto.email())
                .password(newPassword)
                .role("ROLE_USER")
                .build();
    }

    public static MemberResDto.MemberPreviewDto toMemberPreviewDto(Member member){
        return MemberResDto.MemberPreviewDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .role(member.getRole())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    public static MemberResDto.LoginResponseDto toLoginResponseDto(Member member, String accessToken, String refreshToken){
        return MemberResDto.LoginResponseDto.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
