package com.example.umc7th.converter;

import com.example.umc7th.dto.request.MemberRequestDto;
import com.example.umc7th.dto.response.MemberResponseDto;
import com.example.umc7th.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;


public class MemberConverter {
    public static Member toEntity(MemberRequestDto.CreateMemberRequestDto dto, PasswordEncoder passwordEncoder) {
        String newPassword = passwordEncoder.encode(dto.getPassword());
        return Member.builder()
                .email(dto.getEmail())
                .password(newPassword)
                .role("USER")
                .build();
    }

    public static MemberResponseDto.MemberPreviewDto from(Member member){
        return MemberResponseDto.MemberPreviewDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .role(member.getRole())
                .createdAt(member.getCreated_at())
                .updatedAt(member.getUpdated_at())
                .build();
    }

    public static MemberResponseDto.LoginResponseDto from(Member member, String accessToken, String refreshToken){
        return MemberResponseDto.LoginResponseDto.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
