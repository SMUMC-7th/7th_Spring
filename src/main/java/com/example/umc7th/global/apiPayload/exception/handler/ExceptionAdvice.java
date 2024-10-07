package com.example.umc7th.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Array;
import java.util.Arrays;
//전체 구조
//1.service에서 에러enum 받은 generalException등을 이용해서 예외 던지기.
//2.던져진 예외의 타입에 맞게 ExceptionAdvice의 메서드와 자동으로 매핑,(순서대로 처리, 범주가 작은 것부터 작성해야함)
//3.controller에서는 success케이스만 반환
@Slf4j//로그 사용가능
@RestControllerAdvice//이게 에러핸들러다 라는뜻
//클래스의 메서드가 애플리케이션의 모든 컨트롤러에서 발생하는 예외를 자동으로 감지, 예외가 발생하면 해당 예외 타입에 맞는 @ExceptionHandler가 호출
public class ExceptionAdvice {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<String>> customException(GeneralException e){
        BaseErrorCode code = e.getCode();

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, null)
        );

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> exception(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false,null)
        );
    }

//    @ExceptionHandler(Exception.class)
//    public  CustomResponse<String> exception(Exception e){
//        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
//        log.error(Arrays.toString(e.getStackTrace()));
//        return code.getResponse();
//    }
}
