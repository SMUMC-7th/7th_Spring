package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JwtErrorCode implements BaseErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN401", "토큰이 유효하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
