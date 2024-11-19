package com.example.umc7th.domain.openapi.exception;

import com.example.umc7th.domain.openapi.dto.OpenApiResponseDTO;
import com.example.umc7th.global.apiPayload.exception.CustomException;

public class OpenApiException extends CustomException {
    public OpenApiException(OpenApiErrorCode code) {
        super(code);
    }
}
