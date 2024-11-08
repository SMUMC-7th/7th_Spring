package com.example.umc7th.member.exception;

import com.example.umc7th.global.apiPayload.exception.CustomException;

public class MemberException extends CustomException {
    public MemberException(MemberErrorCode code) {
        super(code);
    }
}
