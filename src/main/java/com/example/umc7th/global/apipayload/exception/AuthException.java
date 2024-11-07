package com.example.umc7th.global.apipayload.exception;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;

public class AuthException extends GeneralException{

    public AuthException(BaseErrorCode code) {
        super(code);
    }
}
