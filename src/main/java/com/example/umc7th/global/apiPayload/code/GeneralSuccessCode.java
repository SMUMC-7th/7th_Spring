package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralSuccessCode implements BaseSuccessCode{
    // 일반적인 성공 응답 정의
    OK_200(HttpStatus.OK,
            "SUCCESS200",
            "성공적으로 처리되었습니다"),

    CREATED_201(HttpStatus.CREATED,
                    "SUCCESS201",
                    "성공적으로 생성되었습니다"),

    ACCEPTED_202(HttpStatus.ACCEPTED,
                    "SUCCESS202",
                    "요청이 접수되었습니다"),

    NO_CONTENT_204(HttpStatus.NO_CONTENT,
                    "SUCCESS204",
                    "내용이 없습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
