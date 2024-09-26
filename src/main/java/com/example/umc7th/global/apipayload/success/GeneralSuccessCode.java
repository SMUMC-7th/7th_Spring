package com.example.umc7th.global.apipayload.success;

import com.example.umc7th.global.apipayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    SUCCESS_200(HttpStatus.OK,
            "SUCCESS200",
            "요청이 성공적으로 처리되었습니다"),
    CREATED_201(HttpStatus.CREATED,
            "SUCCESS201",
            "리소스가 성공적으로 생성되었습니다"),
    ACCEPTED_202(HttpStatus.ACCEPTED,
            "SUCCESS202",
            "요청이 접수되었습니다"),
    NO_CONTENT_204(HttpStatus.NO_CONTENT,
            "SUCCESS204",
            "요청은 성공하였고 응답 데이터는 없습니다");



    private final HttpStatus status;
    private final String code;
    private final String message;
}
