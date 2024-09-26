package com.example.umc7th.domain;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name = "테스트")
public class TestController {

    @GetMapping("")
    @Operation(summary = "테스트", description = "문자열을 반환합니다.")
    public String test() {
        return "테스트용 컨트롤러입니다.";
    }
}
