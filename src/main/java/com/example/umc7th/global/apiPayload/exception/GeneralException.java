package com.example.umc7th.global.apiPayload.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
// 자바의 예외에는 Checked Exception 과 Unchecked Exception 이 있음

// Checked Exception <- 컴파일러가 반드시 예외 처리를 강제하는 예외
// Unchecked Exception <- 예외 처리 없이도 컴파일 가능 (논리적인 오류 or 프로그래밍 오류)
// 이런 예외는 주로 RuntimeException을 상속받아 구현된다. (ex. NullPointerException, ArrayIndexOutOfBoundsException)

// GeneralException이 RuntimeException을 상속받으면 Unchecked Excpetion이 되어서
// @RestControllerAdvice 를 사용해 전역 예외처리에서 일괄적으로 핸들링 가능
// 개별적으로 예외 처리하지 않고 중앙에서 통합 처리하도록
public class GeneralException extends RuntimeException{

    // 에러코드 저장
    private final BaseErrorCode code;

    // 에러코드 저장 위한 생성자 정의
    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }
}