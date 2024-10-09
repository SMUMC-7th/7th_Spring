package com.example.umc7th.exception.exception;

import com.example.umc7th.global.apipayload.code.BaseErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class ArticleException extends GeneralException {
    public ArticleException(BaseErrorCode code) {
        super(code);
    }
}
