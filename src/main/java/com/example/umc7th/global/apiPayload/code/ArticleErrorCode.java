package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode{

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "ARTICLE404",
            "존재 하지 않는 게시물입니다."),
    ;

    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;
}
