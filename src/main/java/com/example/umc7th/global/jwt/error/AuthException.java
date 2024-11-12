package com.example.umc7th.global.jwt.error;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class AuthException extends RuntimeException {
    private final BaseErrorCode code;

    public AuthException(BaseErrorCode code) {
        this.code = code;
    }

}
