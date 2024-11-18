package com.example.umc7th.exception.exception;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;

public class OpenApiException extends GeneralException {
    public OpenApiException(BaseErrorCode code) {
        super(code);
    }
}
