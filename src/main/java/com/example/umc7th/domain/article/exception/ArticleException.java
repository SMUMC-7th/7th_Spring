package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class ArticleException extends GeneralException {
    public ArticleException(ArticleErrorCode code) {
        super(code); // 부모 클래스 GeneralException의 생성자를 호출하여 예외 코드를 전달
    }
}