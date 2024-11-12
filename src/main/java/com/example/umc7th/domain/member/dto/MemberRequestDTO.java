package com.example.umc7th.domain.member.dto;

import com.example.umc7th.domain.member.entity.Member;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberRequestDTO {

    @Getter
    public static class MemberSignUpDTO {

        private String email;
        private String password;

        public Member toEntity(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(this.email)
                    .password(passwordEncoder.encode(password))
                    .build();
        }
    }

    @Getter
    public static class MemberLoginDTO {
        private String email;
        private String password;

    }
}
