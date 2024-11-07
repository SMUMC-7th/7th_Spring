package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.apiPayload.exception.CustomException;

public class AuthException extends CustomException {
    public AuthException(JwtErrorCode code) {
        super(code);
    }
}
