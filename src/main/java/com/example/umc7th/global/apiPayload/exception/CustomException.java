package com.example.umc7th.global.apiPayload.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    //예외에서 발생한 에러의 상세 내용
    private final BaseErrorCode code;

    //생성자
    public CustomException(BaseErrorCode code) {
        this.code = code;
    }
}
