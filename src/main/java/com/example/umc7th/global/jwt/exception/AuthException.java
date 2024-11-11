package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class AuthException extends GeneralException {

    public AuthException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
