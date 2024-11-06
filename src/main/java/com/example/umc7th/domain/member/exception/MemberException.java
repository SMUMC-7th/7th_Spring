package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;

public class MemberException extends CustomException {

    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
