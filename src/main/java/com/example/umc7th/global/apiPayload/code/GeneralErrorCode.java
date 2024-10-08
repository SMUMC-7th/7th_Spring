package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
// enum 으로 여러 종류의 오류 코드와 상태 상수로 정의
public enum GeneralErrorCode implements BaseErrorCode{
    BAD_REQUEST_400(HttpStatus.BAD_REQUEST,
            "COMMON400",
            "잘못된 요청입니다"),
    UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED,
            "COMMON401",
            "인증이 필요합니다"),
    FORBIDDEN_403(HttpStatus.FORBIDDEN,
            "COMMON403",
            "접근이 금지되었습니다"),
    NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "COMMON404",
            "요청한 자원을 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR_500(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500",
            "서버 내부 오류가 발생했습니다"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

    // 오류 응답 반환하는 메소드
    @Override
    public <T> CustomResponse<T> getResponse() {
        // 실패 응답 반환하는 정적 메소드
        return CustomResponse.onFailure(this.status, this.code, this.message, false, null);
    }
}
