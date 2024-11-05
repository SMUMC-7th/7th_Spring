package com.example.umc7th.global.jwt;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum JwtErrorCode implements BaseErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,
            "TOKEN401",
            "토큰이 유효하지 않습니다."),
    EMPTY_TOKEN(HttpStatus.UNAUTHORIZED,
            "TOKEN400",
            "헤더에 토큰이 비어 있습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
