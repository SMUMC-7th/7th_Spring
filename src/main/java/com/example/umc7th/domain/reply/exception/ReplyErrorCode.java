package com.example.umc7th.domain.reply.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ReplyErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404",
            "댓글을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
