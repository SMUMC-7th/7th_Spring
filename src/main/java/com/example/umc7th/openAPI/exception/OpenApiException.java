package com.example.umc7th.openAPI.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;

public class OpenApiException extends CustomException {
    public OpenApiException(BaseErrorCode code) {
        super(code);
    }
}
