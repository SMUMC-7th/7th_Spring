package com.example.umc7th.domain.reply.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;

public class ReplyException extends CustomException {

    public ReplyException(ReplyErrorCode code) {
        super(code);
    }
}
