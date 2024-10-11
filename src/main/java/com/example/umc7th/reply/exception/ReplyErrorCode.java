package com.example.umc7th.reply.exception;

import org.springframework.http.HttpStatus;

import com.example.umc7th.global.config.apiPayload.code.BaseErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplyErrorCode implements BaseErrorCode {
	NOT_FOUND(HttpStatus.NOT_FOUND, "REPLY404", "댓글을 찾지 못했습니다."),
		;

	private final HttpStatus status;
	private final String code;
	private final String message;
}
