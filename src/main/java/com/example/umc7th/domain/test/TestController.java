package com.example.umc7th.domain.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트용 API") //해당 컨트롤러가 어떤 컨트롤러인지 표시
public class TestController {

    //Operation은 Controller 내의 메서드 위에 붙임
    //해당 메서드가 어떤 역할을 하는 지 간단하게 설명 기입 가능
    @GetMapping("/test")
    @Operation(summary = "테스트하는 API", description = "테스트 용도이며 Hello World를 반환합니다.")
    public String test() {
        return "Hello World";
    }

}
