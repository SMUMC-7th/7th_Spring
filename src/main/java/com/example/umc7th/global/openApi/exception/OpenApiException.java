package com.example.umc7th.global.openApi.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class OpenApiException extends RuntimeException {
    // 예외에서 발생한 에러의 상세 내용
    private final OpenApiErrorCode code;

    // 생성자
    public OpenApiException(OpenApiErrorCode code) {
        this.code = code;
    }
}


