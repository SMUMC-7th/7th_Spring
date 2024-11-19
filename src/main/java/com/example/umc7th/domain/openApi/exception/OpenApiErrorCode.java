package com.example.umc7th.domain.openApi.exception;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum OpenApiErrorCode implements BaseErrorCode {

    UNSUPPORTED_LANGUAGE(HttpStatus.NOT_FOUND, "OPENAPI400", "지원되지 않는 언어입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public <T> CustomResponse<T> getErrorResponse() {
        return null;
    }
}
