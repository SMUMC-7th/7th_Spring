package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode {

    ARTICLE_NOT_FOUND_404(HttpStatus.NOT_FOUND, 
            "ARTICLE404",
            "요청한 게시글을 찾을 수 없습니다"),
    ARTICLE_DELETED_410(HttpStatus.GONE,
            "ARTICLE410",
            "삭제된 게시글입니다"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
