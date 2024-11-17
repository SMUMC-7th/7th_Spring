package com.example.umc7th.openAPI.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class OpenApiException extends GeneralException {
    public OpenApiException(OpenApiErrorCode code){
        super(code);
    }
}
