package com.example.umc7th.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResponseDTO {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MemberTokenDTO {
        //클라이언트가 두 토큰을 가지고 있다가 전송 시에는 유효시간이 짧은 accessToken만 전송하여 탈취시 피해범위 최소화
        //토큰은 한번 발급하면 유효시간을 수정할 수 없기 때문
        //보통 토큰은 안전한 쿠키에 담아 전송
        String accessToken; // 실제 인증할 때마다 사용하는 토큰, 유효시간을 작게 하여 refreshToken으로 재발급
        String refreshToken;// accessToken이 만료되면 재발급하는 토큰, (로그아웃 시 삭제)
    }
}