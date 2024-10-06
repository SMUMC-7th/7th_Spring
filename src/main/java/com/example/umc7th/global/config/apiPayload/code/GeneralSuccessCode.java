package com.example.umc7th.global.config.apiPayload.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum GeneralSuccessCode implements BaseSuccessCode {
	SUCCESS_200(HttpStatus.OK,
		"COMMON200",
		"요청이 성공적으로 처리되었습니다"),

	CREATED_201(HttpStatus.CREATED,
		"COMMON201",
		"자원이 성공적으로 생성되었습니다"),

	NO_CONTENT_204(HttpStatus.NO_CONTENT,
		"COMMON204",
		"요청이 성공적으로 처리되었지만 반환할 내용이 없습니다");
	private final HttpStatus status;
	private final String code;
	private final String message;

	GeneralSuccessCode(HttpStatus status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
