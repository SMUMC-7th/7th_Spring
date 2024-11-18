package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404",
            "요청한 사용자를 찾을 수 없습니다"),
    EXIST_EMAIL(HttpStatus.CONFLICT,
            "MEMBER409",
                "이미 사용중인 이메일입니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST,
                "MEMBER400",
                "토큰 변경 실패"),
    OAUTH_USER_INFO_FAIL(HttpStatus.NOT_FOUND,
                "MEMBER404_1",
                "사용자 정보를 가져오지 못했습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
