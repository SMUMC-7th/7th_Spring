package com.example.umc7th.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "사용자를 찾을 수 없습니다."),
    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER400", "이미 존재하는 사용자입니다."),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401", "비밀번호가 틀립니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "OAuth500", "잘못된 Oauth 토큰입니다"),
    OAUTH_USER_INFO_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "OAuth500", "OAuth 유저 정보를 가져올 수 없습니다."),
    OAUTH_USER_EMAIL_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "OAuth500", "이메일 정보가 없습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}