package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;

// 인증 관련 예외 처리를 위한 커스텀 예외 클래스
public class AuthException extends GeneralException {

    // JwtErrorCode를 받아 상위 클래스(GeneralException) 생성자를 호출하여 예외 초기화
    public AuthException(JwtErrorCode code) {
        super(code); // 부모 클래스의 생성자를 호출하면서 예외 코드 설정
    }
}