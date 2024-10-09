package com.example.umc7th.exception.code;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {
    // Article관련 ERROR 응답
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "A404", "게시글이 존재하지 않습니다."),
    INVALID_ARTICLE_DATA(HttpStatus.BAD_REQUEST,
            "A400", "게시글 형식이 맞지않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
