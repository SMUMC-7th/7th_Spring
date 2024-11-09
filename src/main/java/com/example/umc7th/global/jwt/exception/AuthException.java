package com.example.umc7th.global.jwt.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class AuthException extends GeneralException {

    public AuthException(JwtErrorCode errorCode){
        super(errorCode);
    }
}
