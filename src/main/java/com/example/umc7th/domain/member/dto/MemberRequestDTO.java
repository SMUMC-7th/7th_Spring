package com.example.umc7th.domain.member.dto;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class MemberLoginDTO {
        String email;
        String password;
    }

    @Getter
    public static class MemberSignUpDTO {
        String email;
        String password;
    }
}
