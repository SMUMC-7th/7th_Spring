package com.example.umc7th.domain.reply.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;

// reply 관련 예외 처리 class
public class ReplyException extends GeneralException {
    // 생성자에서 에러코드 받아 초기화
    public ReplyException(ReplyErrorCode code) {
        super(code);
    }
}