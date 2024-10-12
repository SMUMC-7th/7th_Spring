package com.example.umc7th.controller;


import com.example.umc7th.global.apipayload.CustomResponse;
import com.example.umc7th.global.apipayload.code.GeneralErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;
import com.example.umc7th.global.apipayload.success.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "테스트 API", description = "테스트관련 API")
public class TestController {

    @GetMapping("/test")
    @Operation(method = "GET", summary = "테스트하는 API", description = "Hello World 문자열을 반환합니다.")
    public CustomResponse<?> test(@RequestParam int exception) {
        if(exception == 0){
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST_400);
        } else if (exception == 1) {
            int a = 1/0;
        }
        return CustomResponse.onSuccess(GeneralSuccessCode.SUCCESS_200);
    }

}
