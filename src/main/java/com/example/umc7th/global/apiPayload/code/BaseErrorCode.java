package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

// 오류 코드 관련 처리를 위해 사용되는 인터페이스
public interface BaseErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
