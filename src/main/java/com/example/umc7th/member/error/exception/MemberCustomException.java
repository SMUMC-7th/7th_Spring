package com.example.umc7th.member.error.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import lombok.Getter;

@Getter
public class MemberCustomException extends CustomException {
    public MemberCustomException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }
}
