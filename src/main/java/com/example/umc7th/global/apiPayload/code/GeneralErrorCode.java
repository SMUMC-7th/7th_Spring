package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

// 모든 필드값을 인자로 가지는 생성자 추가
// enum에 필요함, 생성자를 따로 직접 정의해도 무방
@AllArgsConstructor
// Getter 메서드 생성 (interface 오버라이딩을 위함)
@Getter
public enum GeneralErrorCode implements BaseErrorCode {

    // 일반적인 ERROR 응답
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

    // article 관련 Error
    ARTICLE_NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "ARTICLE404",
            "요청한 게시글을 찾을 수 없습니다"),
    
    // reply 관련 Error
    REPLY_NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "REPLY404",
            "요청한 댓글을 찾을 수 없습니다"),
    ;

    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public <T> CustomResponse<T> getResponse() {
        return CustomResponse.onFailure(status, code, message);
    }
}
