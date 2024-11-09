package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
// enum 으로 여러 종류의 오류 코드와 상태를 정의
public enum GeneralErrorCode implements BaseErrorCode{

    BAD_REQUEST_400(HttpStatus.BAD_REQUEST,
            "COMMON400",
            "잘못된 요청입니다"),

    UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED,
            "COMMON401",
            "인증이 필요합니다"),

    FORBIDDEN_403(HttpStatus.FORBIDDEN,
            "COMMON403",
            "접근이 금지되었습니다"),

    NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "COMMON404",
            "요청한 자원을 찾을 수 없습니다"),

    INTERNAL_SERVER_ERROR_500(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500",
            "서버 내부 오류가 발생했습니다"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}

/*
  Enum에 왜 생성자가 필요하지?
  Enum 클래스는 하나의 생성자를 가지지만, 각 상수가 이 생성자에 각기 다른 값을 전달하여 고유 속성을 가질 수 있음
  이 방식으로 Enum을 통해 각기 다른 속성의 상수 집합을 만들 수 있게 되는 것
 */
