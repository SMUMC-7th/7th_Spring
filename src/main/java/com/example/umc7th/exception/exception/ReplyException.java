package com.example.umc7th.exception.exception;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class ReplyException extends GeneralException {
    public ReplyException(BaseErrorCode code) {
        super(code);
    }
}
