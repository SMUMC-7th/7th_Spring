package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {

    SUCCESS_200(HttpStatus.OK,
            "COMMON200",
            "요청이 성공적으로 처리되었습니다"),
    CREATED_201(HttpStatus.CREATED,
            "COMMON201",
            "요청이 성공적으로 생성되었습니다"),
    ACCEPTED_202(HttpStatus.ACCEPTED,
            "COMMON202",
            "요청이 접수되었습니다"),
    NO_CONTENT_204(HttpStatus.NO_CONTENT,
            "COMMON204",
            "요청이 성공적으로 처리되었으나, 응답할 내용이 없습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
