package com.example.umc7th.domain.member.converter;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberConverter {

    public static MemberResponseDTO.CreateMemberResultDTO toCreateMemberResultDTO(Member member) {

        return MemberResponseDTO.CreateMemberResultDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MemberViewDTO toMemberViewDTO(Member member) {

        return MemberResponseDTO.MemberViewDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.CreateMemberDTO createMemberDTO, BCryptPasswordEncoder encoder) {

        String encodedPassword = encoder.encode(createMemberDTO.getPassword());

        return Member.builder()
                .email(createMemberDTO.getEmail())
                .password(encodedPassword)
                .build();

    }

    public static MemberResponseDTO.LoginResponseDTO toLoginResponseDTO(Member member, String accessToken, String refreshToken) {

        return MemberResponseDTO.LoginResponseDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
