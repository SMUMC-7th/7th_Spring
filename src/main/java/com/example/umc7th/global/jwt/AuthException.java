package com.example.umc7th.global.jwt;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;

public class AuthException extends RuntimeException {

    private final BaseErrorCode code;


    public AuthException(BaseErrorCode code) {
        this.code = code;
    }
}
