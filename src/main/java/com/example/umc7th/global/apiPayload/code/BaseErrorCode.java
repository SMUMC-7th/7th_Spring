package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

// 다른 에러들을 추상화 할 인터페이스 생성
public interface BaseErrorCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();


    <T> CustomResponse<T> getResponse();
}
