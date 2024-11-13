package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404_0", "해당 게시글을 찾을 수 없습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.NOT_FOUND, "MEMBER404_2", "사용자 정보 조회 실패"),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER400_1", "토큰 변환 실패"),
    OAUTH_EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER400_2", "이메일 정보를 찾을 수 없습니다."),
    OAUTH_LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "로그인에 실패하였습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
