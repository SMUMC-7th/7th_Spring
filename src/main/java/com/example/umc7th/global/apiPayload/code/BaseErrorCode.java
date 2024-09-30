package com.example.umc7th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

// 다른 에러들을 추상화할 인터페이스를 만들어주시면 됩니다.
public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();
}