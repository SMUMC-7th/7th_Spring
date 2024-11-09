package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(MemberErrorCode code) {
        super(code);
    }
}