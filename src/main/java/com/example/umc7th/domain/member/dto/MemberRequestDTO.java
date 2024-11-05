package com.example.umc7th.domain.member.dto;

import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class SignUpDTO {
        private String email;
        private String password;
    }

    @Getter
    public static class LoginDTO {
        private String email;
        private String password;
    }

}
