package com.example.umc7th.exception.code;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND,
            "R404", "댓글이 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
