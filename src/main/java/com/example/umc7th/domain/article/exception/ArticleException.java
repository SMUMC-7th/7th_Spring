package com.example.umc7th.domain.article.exception;

import com.example.umc7th.global.apiPayload.exception.CustomException;

public class ArticleException extends CustomException {
	public ArticleException(ArticleErrorCode code) {
		super(code);
	}
}
