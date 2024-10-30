package com.example.umc7th.article.error;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class ArticleCustomException extends RuntimeException {
    private final BaseErrorCode code;

    public ArticleCustomException(BaseErrorCode code) {
        this.code = code;
    }

}
