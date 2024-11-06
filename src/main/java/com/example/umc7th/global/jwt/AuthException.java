package com.example.umc7th.global.jwt;

import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class AuthException extends GeneralException{
    public AuthException(JwtErrorCode code) {
        super(code);
    }
}