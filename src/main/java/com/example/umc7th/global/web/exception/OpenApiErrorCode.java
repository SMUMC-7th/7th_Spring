package com.example.umc7th.global.web.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OpenApiErrorCode implements BaseErrorCode {

    UNSUPPORTED_LANGUAGE(HttpStatus.NOT_FOUND,
            "OPENAPI400",
            "요청한 댓글을 찾을 수 없습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
