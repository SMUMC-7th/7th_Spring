package com.example.umc7th.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberLoginRequest {
    private String email;
    private String password;
}