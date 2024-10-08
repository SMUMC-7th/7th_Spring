package com.example.umc7th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

// 성공 코드 관련 처리를 위해 사용되는 인터페이스
public interface BasSuccessCode {
    // HTTP 상태 코드 반환
    HttpStatus getStatus();
    // 성공 코드에 대한 코드 반환
    String getCode();
    // 성공에 대한 설명 메세지 반환
    String getMessage();
}
