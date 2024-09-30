package com.example.umc7th.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorcode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j // 로그 사용 가능
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<Object>> customException(GeneralException e){
        BaseErrorCode code = e.getCode();

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false,null)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<Object>> exception(Exception e){
        BaseErrorCode code = GeneralErrorcode.INTERNAL_SERVER_ERROR_500;

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false,null)
        );
    }


}
