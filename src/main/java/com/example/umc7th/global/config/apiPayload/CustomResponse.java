package com.example.umc7th.global.config.apiPayload;

import org.springframework.http.HttpStatus;

import com.example.umc7th.global.config.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

	//생성자로 객체를 생성하는 것을 막기

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"status", "code", "message", "isSuccess", "result"})
public class CustomResponse<T> {

	@JsonProperty("status")
	private final HttpStatus status;
	@JsonProperty("code")
	private final String code;
	@JsonProperty("message")
	private final String message;
	@JsonProperty("isSuccess")
	private final boolean isSuccess;
	@JsonProperty("result")
	private final T result;



	public static <T> CustomResponse<T> onSuccess(T result) {
		return new CustomResponse<>(HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), true, result);
	}

	public static <T> CustomResponse<T> of(BaseSuccessCode code, T result) {
		return new CustomResponse<>(code.getStatus(), code.getCode(), code.getMessage(), true, result);
	}

	public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
		return new CustomResponse<>(status, code, message, isSuccess, result);
	}
}


