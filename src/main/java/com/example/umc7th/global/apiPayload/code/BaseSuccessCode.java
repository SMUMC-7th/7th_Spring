package com.example.umc7th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

// 성공 코드 관련 처리를 위해 사용되는 인터페이스
public interface BaseSuccessCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
