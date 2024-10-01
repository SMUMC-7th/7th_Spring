package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
//    <T> CustomResponse<T> getResponse();

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
