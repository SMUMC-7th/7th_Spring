package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
    <T>CustomResponse<T> getResponse();
}