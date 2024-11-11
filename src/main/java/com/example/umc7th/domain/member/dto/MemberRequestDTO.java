package com.example.umc7th.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

public class MemberRequestDTO {


    @Getter
    public static class CreateMemberDTO {
        private String email;
        private String password;
    }

    @Getter
    public static class LoginRequestDTO {
        private String email;
        private String password;
    }

    @Getter
    public static class UpdateMemberDTO {
        private String email;
        private String password;
    }
}
