package com.example.umc7th.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API", description = "테스트관련 API")
public class TestController {

    @GetMapping("/test")
    @Operation(method = "GET", summary = "테스트하는 API", description = "Hello World 문자열을 반환합니다.")
    public String test() {
        return "Hello World";
    }
}
