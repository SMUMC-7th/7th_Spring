package com.example.umc7th.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

// ErrorHandler
@Slf4j
@RestControllerAdvice // RestController 에서 터진 Error 를 잡아서 처리
public class ExceptionAdvice {

    // 처리 로직
    // 파라미터로 Exception 을 가져올 수 있음
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomResponse<String>> customException(CustomException e) {
        BaseErrorCode code = e.getCode();

        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.badRequest().body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage()));
    }

    // Exception도 잡아줘야함
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> exception(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage())
        );
    }

}
