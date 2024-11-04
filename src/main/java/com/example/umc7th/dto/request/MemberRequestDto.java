package com.example.umc7th.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRequestDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberRequestDto {
        public String email;
        public String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRequestDto {
        public String email;
        public String password;
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateMemberRequestDto {
        public Long id;
        public String email;
        public String password;
    }
}
