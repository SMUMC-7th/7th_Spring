package com.example.umc7th.domain.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // 해당 클래스가 ResponseBody를 사용하는 Controller임을 나타낸다
@Tag(name = "테스트용 API")
public class TestController {

	@GetMapping ("/test") // 해당 URL로 보낸 요청을 받고, 이것을 처리하는 메서드 임을 명시
	@Operation(summary = "테스트 하는 API", description = "테스트용도이며 Hello World를 반환합니다.")
	public String test() {
		return "Hello World";
	}
}
