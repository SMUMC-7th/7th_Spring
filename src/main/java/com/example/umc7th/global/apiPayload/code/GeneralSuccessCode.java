package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode{
    OK_200(HttpStatus.OK,
            "SUCCESS200",
            "요청이 처리되었습니다."),
    CREATED_201(HttpStatus.CREATED,
            "SUCCESS201",
            "리소스가 생성되었습니다."),
    ACCEPTED_202(HttpStatus.ACCEPTED,
            "SUCCESS202",
            "요청이 수락되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
