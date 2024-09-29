package com.example.umc7th.global.config.apiPayload;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

	//생성자로 객체를 생성하는 것을 막기

	@AllArgsConstructor(access = AccessLevel.PRIVATE) // 클래스의 모든 필드를 매개변수로 받는 생성자를 자동으로 생성해준다.

	//Json 형식으로 필드(메개변수) 줄때 어떤 순서로, 어떤 변수들을 줄 것인지 결정
	@JsonPropertyOrder({"isSuccess", "status", "code", "message", "result"})
	public class CustomResponse <T> {
		@JsonProperty("isSuccess") // isSuccess라는 변수라는 것을 명시하는 Annotation
		private boolean isSuccess;

		@JsonProperty("status")
		private HttpStatus status;

		@JsonProperty("code")
		private String code;

		@JsonProperty("message")
		private String message;

		@JsonProperty("result") // 실제 데이터를 담는 변수
		private T result;

		//성공 메서드
		public static <T> CustomResponse<T> onSuccess(T result) {
			return new CustomResponse<>(true, HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), result);
		}

		// 실패 메서드
		public static <T> CustomResponse<T> onFailure(String code, String message) {
			return new CustomResponse<>(false, HttpStatus.BAD_REQUEST, code, message, null);
		}
	}


