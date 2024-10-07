package com.example.umc7th.global.apiPayload.code;

import org.springframework.http.HttpStatus;
//success 인터페이스
public interface BaseSuccessCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
