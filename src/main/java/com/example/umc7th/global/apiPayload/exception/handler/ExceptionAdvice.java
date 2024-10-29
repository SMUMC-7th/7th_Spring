package com.example.umc7th.global.apiPayload.exception.handler;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<CustomResponse<String>> customException(GeneralException e) {
        BaseErrorCode code = e.getCode();
        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(false, code.getStatus(), code.getCode(), code.getMessage(), null)
        );
//        return ResponseEntity.badRequest().body(code.getResponse());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse<String>> exception(Exception e) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR_500;
        log.error(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse.onFailure(false, code.getStatus(), code.getCode(), code.getMessage(), null)
        );
    }
}
