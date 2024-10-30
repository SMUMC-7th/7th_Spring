package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.exception.GeneralException;

/**
 * 게시글 관련 예외를 처리하는 ArticleException class
 */
public class ArticleException extends GeneralException {

    /**
     * ArticleException 생성자
     * @param code (발생한 오류의 코드)
     */
    public ArticleException(ArticleErrorCode code) {
        super(code);
    } // 부모 클래스의 생성자 호출
}