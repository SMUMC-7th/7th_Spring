package com.example.umc7th.domain.member.converter;

import com.example.umc7th.domain.member.dto.MemberDto;
import com.example.umc7th.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter {
    public Member toEntity(MemberDto memberDto, String encodedPassword) {
        return Member.builder()
                .email(memberDto.getEmail())
                .password(encodedPassword) //비번 인코딩
                .build();
    }
}
