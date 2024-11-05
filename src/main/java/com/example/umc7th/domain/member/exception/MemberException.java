package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class MemberException extends GeneralException {

    public MemberException(MemberErrorCode errorCode){
        super(errorCode);
    }
}
