package com.example.umc7th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "성공했습니다."),
    CREATE(HttpStatus.CREATED, "COMMON201", "생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}