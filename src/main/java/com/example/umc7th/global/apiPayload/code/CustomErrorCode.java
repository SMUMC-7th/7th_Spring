package com.example.umc7th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomErrorCode implements BaseErrorCode{
    //Article
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "ARTICLE404",
            "존재 하지 않는 게시물입니다."),

    //Reply
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REPLY404",
            "존재 하지 않는 댓글입니다."),
    ;


    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;
}
