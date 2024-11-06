package com.example.umc7th.domain.member.dto;

import com.example.umc7th.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponse {
    private Long id;
    private String email;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
    }
}