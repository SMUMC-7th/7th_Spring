package com.example.umc7th.domain.openapi.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OpenApiErrorCode implements BaseErrorCode {

    UNSUPPORTED_LANGUAGE(HttpStatus.BAD_REQUEST, "TOUR400", "지원하지 않는 언어입니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
