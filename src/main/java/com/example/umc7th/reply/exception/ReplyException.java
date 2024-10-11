package com.example.umc7th.reply.exception;

import com.example.umc7th.global.apiPayload.exception.CustomException;

public class ReplyException extends CustomException {
    public ReplyException(ReplyErrorCode code) {
        super(code);
    }
}
