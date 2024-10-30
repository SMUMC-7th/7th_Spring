package com.example.umc7th.article.error;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode {
    //400
    ARTICLE_BAD_REQUEST_400(HttpStatus.BAD_REQUEST,
            "ARTICLE400",
            "잘못된 요청입니다"),
    //401
    ARTICLE_UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED,
            "ARTICLE401",
            "해당 Article 에 인증이 필요합니다"),
    //403
    ARTICLE_FORBIDDEN_403(HttpStatus.FORBIDDEN,
            "ARTICLE403",
            "해당 Article 에 대한 접근이 금지되었습니다"),
    //404
    ARTICLE_NOT_FOUND_404(HttpStatus.NOT_FOUND,
            "ARTICLE404",
            "해당하는 Article 이 없습니다."),
    ;
    // 필요한 필드값 선언
    private final HttpStatus status;
    private final String code;
    private final String message;

}
