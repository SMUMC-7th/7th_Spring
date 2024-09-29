package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum BaseSuccessCode implements BaseErrorCode{

    OK(HttpStatus.OK,
            "200",
            "Success"),
    CREATE(HttpStatus.CREATED,
            "201",
            "Resource Created"),
    ACCEPTED(HttpStatus.ACCEPTED,
            "202",
            "Request Accepted");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

