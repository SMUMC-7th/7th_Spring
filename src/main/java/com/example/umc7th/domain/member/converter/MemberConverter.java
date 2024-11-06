package com.example.umc7th.domain.member.converter;

import com.example.umc7th.domain.member.dto.MemberReqDTO;
import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberConverter {
    //dto -> entity
    public static Member toMember(MemberReqDTO.SignupReqDTO dto, PasswordEncoder passwordEncoder){
        String newPassword = passwordEncoder.encode(dto.password());
        return Member.builder()
                .email(dto.email())
                .password(newPassword)
                .build();
    }

    //entity -> dto
    public static MemberResDTO.MemberPreviewDTO toMemberDTO(Member member){
        return MemberResDTO.MemberPreviewDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    public static MemberResDTO.LoginResDTO toLoginDTO(Member member, String accessToken, String refreshToken){
        return MemberResDTO.LoginResDTO.builder()
                .id(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
