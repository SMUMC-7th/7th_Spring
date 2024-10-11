package com.example.umc7th.domain.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.umc7th.global.config.apiPayload.CustomResponse;
import com.example.umc7th.global.config.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.config.apiPayload.exception.CustomException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 해당 클래스가 ResponseBody를 사용하는 Controller임을 나타낸다
@Tag(name = "테스트용 API")
public class TestController {

	@GetMapping("/test")
	public CustomResponse<String> test() {
		return CustomResponse.onSuccess("Hello World");
	}

	@GetMapping("/test/failure")
	public CustomResponse<String> failure(@RequestParam int exception) {
		if (exception == 0) {
			throw new CustomException(GeneralErrorCode.BAD_REQUEST_400);
		}
		else if (exception == 1) {
			int a = 1 / 0;
		}
		return CustomResponse.onSuccess("에러 핸들러 동작하지 않음");
	}

}
