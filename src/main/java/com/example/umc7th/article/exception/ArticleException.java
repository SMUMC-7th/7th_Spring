package com.example.umc7th.article.exception;

import com.example.umc7th.global.config.apiPayload.exception.CustomException;

public class ArticleException  extends CustomException {
	public ArticleException(ArticleErrorCode code) {
		super(code);
	}
}
