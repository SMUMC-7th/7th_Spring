package com.example.umc7th.article.exception;

import org.springframework.http.HttpStatus;

import com.example.umc7th.global.config.apiPayload.code.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleErrorCode implements BaseErrorCode {
	NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE404", "게시글을 찾지 못했습니다."),
	;

	private final HttpStatus status;
	private final String code;
	private final String message;

}
