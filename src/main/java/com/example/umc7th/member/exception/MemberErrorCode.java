package com.example.umc7th.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "존재하지 않는 회원입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}