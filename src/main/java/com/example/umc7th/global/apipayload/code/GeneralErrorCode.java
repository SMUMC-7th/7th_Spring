package com.example.umc7th.global.apipayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum GeneralErrorCode implements BaseErrorCode {


    // 일반적인 ERROR 응답
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


    // Article관련 ERROR 응답
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "A404", "게시글이 존재하지 않습니다."),
    INVALID_ARTICLE_DATA(HttpStatus.BAD_REQUEST,
            "A400", "게시글 형식이 맞지않습니다."),

    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND,
            "R404", "댓글이 존재하지 않습니다.");






    private final HttpStatus status;
    private final String code;
    private final String message;

}
