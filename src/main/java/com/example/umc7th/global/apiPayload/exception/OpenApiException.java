package com.example.umc7th.global.apiPayload.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class OpenApiException extends RuntimeException {

    private final BaseErrorCode code;

    public OpenApiException(BaseErrorCode code) {
        this.code = code;
    }
}
