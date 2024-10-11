package com.example.umc7th.reply.exception;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "REPLY404", "댓글을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    @Override
    public <T> CustomResponse<T> getResponse() {
        return null;
    }
}
