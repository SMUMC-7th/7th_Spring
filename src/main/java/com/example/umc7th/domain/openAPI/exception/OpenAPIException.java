package com.example.umc7th.domain.openAPI.exception;

import com.example.umc7th.global.apiPayload.exception.CustomException;

public class OpenAPIException extends CustomException {
    public OpenAPIException(OpenAPIErrorCode code) {
        super(code);
    }
}
