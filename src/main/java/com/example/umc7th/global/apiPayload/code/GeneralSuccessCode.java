package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode {

    OK_200(HttpStatus.OK,
        "SUCCESS200",
            "요청이 성공적으로 처리되었습니다"),
    CREATED_201(HttpStatus.CREATED,
            "SUCCESS201",
            "요청으로 새로운 리소스가 생성되었습니다"),
    ACCEPTED_202(HttpStatus.ACCEPTED,
            "SUCCESS202",
            "요청을 수신하였습니다"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
