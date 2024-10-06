package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

// 다른 에러들을 추상화 할 인터페이스
// 현재 status, code, message를 가져오는 method만 있는데 직접 만든 응답을 반환하는 코드가 더 좋을 수 있음
public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

    <T> CustomResponse<T> getResponse();
}


