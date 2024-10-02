package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class ArticleException extends GeneralException {

    public ArticleException(ArticleErrorCode errorCode){
        super(errorCode);
    }
}
