package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;

public class ArticleException extends GeneralException { // to ArticleException(?

    public ArticleException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
