package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
// enum 으로 여러 종류의 성공 코드와 상태를 정의
public enum GeneralSuccessCode implements BaseSuccessCode {


    OK(HttpStatus.OK, "COMMON200", "성공했습니다."),
    CREATE(HttpStatus.CREATED, "COMMON201", "생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
