package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public interface BaseSuccessCode {

    HttpStatus getStatus();

    String getCode();

    String getMessage();
}
