package com.example.umc7th.global.apiPayload.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private BaseErrorCode code;

    public CustomException(BaseErrorCode code) {
        this.code = code;
    }

}