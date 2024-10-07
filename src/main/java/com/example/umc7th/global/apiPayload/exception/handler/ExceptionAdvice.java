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

@Slf4j // 롬복을 이용해 자동으로 로그 객체 생성
@RestControllerAdvice // 모든 controller에서 발생하는 예외를 한 곳에서 처리하기 위한 advice class
// 예외 처리 로직을 담당하는 클래스
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class) // GeneralException이 발생했을 때 이 메소드가 호출되도록 설정
    // GeneralException 처리하는 메소드
    public ResponseEntity<CustomResponse<Object>> customException(GeneralException e){

        BaseErrorCode code = e.getCode(); // GeneralEsception에서 발생한 에러 코드 정보

        // 예외 발생 시 stack trace를 로그로 출력해서 에러 발생 위치 추적
        log.error(Arrays.toString(e.getStackTrace()));

        // HTTP 상태 코드 400과 함께 오류 응답 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                // CustomResponse 실패 응답 생성
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false,null)
        );
    }

    @ExceptionHandler(Exception.class) // 모든 Exception 발생 시 이 메소드 호출
    // Exception 처리 메소드
    public ResponseEntity<CustomResponse<Object>> exception(Exception e){
        // 예외 발생 시 기본적으로 500 (서버 내부 오류)
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false,null)
        );
    }


}
