package com.example.umc7th.controller.global.apiPayload.exception;

import com.example.umc7th.controller.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;

    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }

}
