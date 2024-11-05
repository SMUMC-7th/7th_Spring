package com.example.umc7th.member.error;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409", "이메일이 이미 존재합니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "MEMBER400", "유효하지 않은 입력값입니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER40402", "해당 회원이 존재하지 않습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}

