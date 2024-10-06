package com.example.umc7th.global.config.apiPayload.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 모든 필드값을 메개변수로 가지는 생성자 추가(enum에 필요, 생성자를

@AllArgsConstructor // 각 필드에 대한 생성자를 자동으로 만들어줌
	// Getter method 생성 (interface 오버라이딩을 위해 사용했습니다.)
	@Getter // 롬복 라이브러리에서 제공하는 기능중 하나, 자동으로 게터 메서드를 생성해줌
public enum GeneralErrorCode implements BaseErrorCode {
	// 일반적인 ERROR 응답 (다 만들지 않으셔도 됩니다.)
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
	// 필요한 필드값 선언 // 각 enum 생성자를 통해 들어온 필드 값들은 저장하기 위해서
	private final HttpStatus status;
	private final String code;
	private final String message;
}
