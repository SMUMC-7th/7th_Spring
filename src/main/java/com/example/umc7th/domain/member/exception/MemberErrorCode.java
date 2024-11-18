package com.example.umc7th.domain.member.exception;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4041", "회원을 찾을 수 없습니다."),
    OAUTH_TOKEN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER4001", "토큰 변환에 실패했습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.BAD_REQUEST, "MEMBER4042", "사용자 정보를 가져오는 데 실패했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public <T> CustomResponse<T> getErrorResponse() {
        return null;
    }
}
