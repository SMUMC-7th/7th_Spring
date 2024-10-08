package com.example.umc7th.domain.reply.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class ReplyException extends GeneralException {

    public ReplyException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
