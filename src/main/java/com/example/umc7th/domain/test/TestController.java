package com.example.umc7th.domain.test;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트용 API")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "테스트하는 API", description = "테스트용도이며 Hello World를 반환합니다.")
    public String test() {
        return "Hello World";
    }
}
