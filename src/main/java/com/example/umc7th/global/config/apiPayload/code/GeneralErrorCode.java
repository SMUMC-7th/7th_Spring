package com.example.umc7th.global.config.apiPayload.code;

import org.springframework.http.HttpStatus;

import com.example.umc7th.global.config.apiPayload.CustomResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 모든 필드값을 메개변수로 가지는 생성자 추가(enum에 필요, 생성자를

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {
	BAD_REQUEST_400(HttpStatus.BAD_REQUEST,
		"COMMON400",
		"잘못된 요청입니다"),
	UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED,
		"COMMON401",
		"인증이 필요합니다"),
	FORBIDDEN_403(HttpStatus.FORBIDDEN,
		"COMMON403",
		"접근이 금지되었습니다"),
	NOT_FOUND_404(HttpStatus.NOT_FOUND,
		"COMMON404",
		"요청한 자원을 찾을 수 없습니다"),
	INTERNAL_SERVER_ERROR_500(
		HttpStatus.INTERNAL_SERVER_ERROR,
		"COMMON500",
		"서버 내부 오류가 발생했습니다"),
	;

	private final HttpStatus status;
	private final String code;
	private final String message;

	@Override
	public <T> CustomResponse<T> getResponse() {
		return CustomResponse.onFailure(this.status, this.code, this.message, false, null);
	}
}
