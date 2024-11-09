package com.example.umc7th.domain.test;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public CustomResponse<String> test() {
        return CustomResponse.onSuccess("Hello World");
    }

    @GetMapping("/test/failure")
    public CustomResponse<String> failure(@RequestParam int exception) {
        if (exception == 0) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST_400);
        }
        else if (exception == 1) {
            int a = 1 / 0;
        }
        return CustomResponse.onSuccess("에러 핸들러 동작하지 않음");
    }
}