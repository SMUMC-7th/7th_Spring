package com.example.umc7th.global.apipayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum JwtErrorCode implements BaseErrorCode{

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "TOKEN401", "토큰이 유효하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
