package com.example.umc7th.global.apipayload.exception;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{
    // 예외에서 발생한 에러의 상세 내용
    private final BaseErrorCode code;

    // 생성자
    public GeneralException(BaseErrorCode code) {
        this.code = code;
    }
}
