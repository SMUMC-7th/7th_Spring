package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor

public enum MemberErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "사용자를 찾을 수 없습니다."),
    ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 존재하는 사용자입니다."),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401", "비밀번호가 틀립니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER400_2", "토큰 변환에 실패했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.NOT_FOUND, "MEMBER404_2", "사용자 정보 조회에 실패했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
