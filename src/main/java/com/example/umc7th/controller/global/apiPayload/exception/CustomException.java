package com.example.umc7th.controller.global.apiPayload.exception;

import com.example.umc7th.controller.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final BaseErrorCode code;

    public CustomException(BaseErrorCode code) {
        this.code = code;
    }

}
