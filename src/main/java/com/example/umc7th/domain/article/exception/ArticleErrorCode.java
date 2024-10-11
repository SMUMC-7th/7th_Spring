package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * 게시글 관련 오류 코드 및 메세지를 정의하는 enum class
 */
@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404", "게시글을 찾지 못했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}