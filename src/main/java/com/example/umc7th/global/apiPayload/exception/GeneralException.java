package com.example.umc7th.global.apiPayload.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
// 자바의 예외에는 Checked Exception 과 Unchecked Exception 이 있음
// Checked Exception <- 컴파일러가 반드시 예외 처리를 요구하는 예외
// Unchecked Exception <- 예외 처리 없이도 컴파일 되고 실행될 수 있는 예외
// RuntimeException은 체크되지 않는 예외를 말하며 이걸 사용하면,
// GeneralException을 던질 때 개발자가 명시적으로 try-catch로 처리하지 않아도 됨
public class GeneralException extends RuntimeException{

    // 에러코드 저장
    private final BaseErrorCode code;

    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }
}