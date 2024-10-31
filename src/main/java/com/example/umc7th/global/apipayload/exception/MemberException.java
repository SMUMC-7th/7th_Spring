package com.example.umc7th.global.apipayload.exception;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;

public class MemberException extends GeneralException{
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
