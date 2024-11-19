package com.example.umc7th.domain.openApi.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class OpenApiException extends GeneralException {
    public OpenApiException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
