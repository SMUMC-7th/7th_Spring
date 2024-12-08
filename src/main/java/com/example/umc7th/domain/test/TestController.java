package com.example.umc7th.domain.test;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ??????
 * ??????
 * ?????
 * ?????
 * ??????
 * ??????/
 * ?????/
 * ??????
 * ?????
 * ???
 * ???
 * ?
 * ????
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public CustomResponse<String> test() {
        /**
         * conflict 해결 해주세요
         */
        return CustomResponse.onSuccess("Hello World");
    }

    @GetMapping("/test/failure")
    public CustomResponse<String> failure(@RequestParam int exception) {
        if (exception == 0) {
            throw new CustomException(GeneralErrorCode.BAD_REQUEST_400);
        }
        else if (exception == 1) {
            // 이건 좀..
            int a = 1 / 0;
            // 0을 나누는 건가?
        }
        return CustomResponse.onSuccess("에러 핸들러 동작하지 않음");
    }
}