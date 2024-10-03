package com.example.umc7th.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestControllerAdvice // restController로 들어온 에러 처리해주는 핸들러 역할을 해준다
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomResponse<String>> customException(CustomException e) {
        BaseErrorCode code = e.getCode();
        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, null)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> customException(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, null)
        );

    }
}
