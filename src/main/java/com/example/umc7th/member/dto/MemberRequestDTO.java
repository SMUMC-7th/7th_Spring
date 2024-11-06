package com.example.umc7th.member.dto;

import com.example.umc7th.member.entity.Member;
import lombok.Getter;

public class MemberRequestDTO {

    @Getter
    public static class SignUpDTO {
        private String email;
        private String password;

        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .email(this.email)
                    .password(encodedPassword)
                    .build();
        }
    }

    @Getter
    public static class LoginDTO {
        private String email;
        private String password;
    }
}
