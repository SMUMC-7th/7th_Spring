package com.example.umc7th.global.apipayload.exception;

import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import com.example.umc7th.global.apipayload.code.GeneralErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<Void>> customException(GeneralException e) {
        BaseErrorCode code = e.getCode();
        log.warn("[WARNING] Custom Exception : {}", e.getCode());
        return ResponseEntity.status(code.getStatus()).body(code.errorResponse());
    }

    // CustomException 이외의 Exception 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<Void>> customException(Exception e) {
        log.warn("[WARNING] Internal Server Error : {} ", e.getMessage());
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        return ResponseEntity.status(code.getStatus()).body(code.errorResponse());
    }
}