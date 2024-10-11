package com.example.umc7th.domain.reply.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {

    // reply 관련 Error
    REPLY_NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "REPLY404",
            "요청한 댓글을 찾을 수 없습니다"),
    REPLY_DELETED_410(HttpStatus.GONE,
            "REPLY410",
                    "삭제된 댓글입니다"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
