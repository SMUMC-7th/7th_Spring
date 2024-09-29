package com.example.umc7th.global.config.apiPayload.exception;

import com.example.umc7th.global.config.apiPayload.code.BaseErrorCode;

public class GeneralException extends RuntimeException {
	// 예외에서 발생한 에러의 상세 내용
	private final BaseErrorCode code;

	// 생성자
	public GeneralException(BaseErrorCode code) {
		this.code = code;
	}
}
