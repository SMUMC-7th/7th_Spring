package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;
public class ArticleException extends GeneralException{
    public ArticleException(ArticleErrorCode code) {
        super(code);
    }
}
