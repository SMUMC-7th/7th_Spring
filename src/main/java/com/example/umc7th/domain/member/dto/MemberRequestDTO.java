package com.example.umc7th.domain.member.dto;

import lombok.Getter;

// 클라이언트에서 서버로 요청할 때 사용
public class MemberRequestDTO {

    // 로그인 요청 시 필요한 정보
    @Getter
    public static class MemberLoginDTO {
        String email;
        String password;
    }

    // 회원가입 요청 시 필요한 정보
    @Getter
    public static class MemberSignUpDTO {
        String email;
        String password;
    }
}