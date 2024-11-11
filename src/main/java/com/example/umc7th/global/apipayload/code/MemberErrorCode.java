package com.example.umc7th.global.apipayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode{

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "맴버를 찾을 수 없습니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER400_1", "토큰 변환 실패"),
    OAUTH_USER_INFO_FAIL(HttpStatus.NOT_FOUND, "MEMBER404_2", "사용자 정보 조회 실패");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
