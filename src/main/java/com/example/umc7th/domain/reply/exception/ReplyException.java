package com.example.umc7th.domain.reply.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class ReplyException extends GeneralException {

    public ReplyException(ReplyErrorCode errorCode) {
        super(errorCode);
    }
}
