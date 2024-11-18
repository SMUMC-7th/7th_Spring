package com.example.umc7th.domain.openAPI.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OpenAPIErrorCode implements BaseErrorCode {

    UNSUPPORTED_LANGUAGE(HttpStatus.NOT_FOUND, "OpenAPI404", "댓글을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}