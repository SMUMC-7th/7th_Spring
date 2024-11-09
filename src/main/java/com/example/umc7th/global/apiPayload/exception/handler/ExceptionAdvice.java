package com.example.umc7th.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j // 로그 기능을 사용할 수 있도록
@RestControllerAdvice // 모든 controller에서 발생하는 예외를 처리하도록
public class ExceptionAdvice {

    // GeneralException이 발생했을 때 호출되는 메서드
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<String>> generalException(GeneralException e) {
        BaseErrorCode code = e.getCode(); // 예외에 담긴 에러 코드 가져옴

        log.error(Arrays.toString(e.getStackTrace())); // 예외 스택 트레이스를 로그에 출력하여 디버깅에 도움을 줌

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( // HTTP 상태 코드를 400 (BAD_REQUEST)로 설정하여 응답을 반환
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false,null) // 실패 응답 생성하여 반환
        );
//        return ResponseEntity.badRequest().body(code.getResponse());
    }

    // 일반적인 예외가 발생했을 때 호출되는 메서드
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> exception(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500; // 기본 에러 코드를 500 (내부 서버 오류)로 설정

        log.error(Arrays.toString(e.getStackTrace())); // 예외 스택 트레이스를 로그에 출력하여 디버깅에 도움을 줌

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( // HTTP 상태 코드를 500 (INTERNAL_SERVER_ERROR)로 설정하여 응답을 반환
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, null) // 실패 응답을 생성하여 반환함
        );
    }
}