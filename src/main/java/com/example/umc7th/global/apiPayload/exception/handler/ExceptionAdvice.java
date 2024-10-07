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

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    // 커스텀 exception 응답
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<String>> customException(GeneralException e) {
        BaseErrorCode code = e.getCode();

        log.error(Arrays.toString(e.getStackTrace()));
        // log.error(e.getMessage()); 도 가능

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, null));
        // return code.getErrorResponse();
    }

    // 전체 exception 응답
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> Exception(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;

        log.error(Arrays.toString(e.getStackTrace()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CustomResponse.onFailure(code.getStatus(), code.getCode(), code.getMessage(), false, null));
    }
}
