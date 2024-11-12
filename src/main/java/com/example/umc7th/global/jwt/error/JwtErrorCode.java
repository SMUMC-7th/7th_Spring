package com.example.umc7th.global.jwt.error;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum JwtErrorCode implements BaseErrorCode {
    //400
    JWT_BAD_REQUEST_400(HttpStatus.BAD_REQUEST,
            "JWT400",
            "잘못된 형식의 토큰입니다."),
    //401
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,
            "JWT401",
            "토큰이 올바르지 않습니다."),
    ;
    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;

}
