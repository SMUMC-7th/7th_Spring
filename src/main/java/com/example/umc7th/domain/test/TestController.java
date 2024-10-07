package com.example.umc7th.domain.test;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 그냥 @Controller와 다르게 RESTful 웹 서비스에서 주로 사용되고, 뷰가 아닌 데이터를 반환함
@RestController
@Tag(name = "테스트용 API")
public class TestController {

    @GetMapping("/test")
    @Operation(summary = "테스트하는 API", description = "테스트 용도이며 Hello World를 반환합니다.")
    // CustomResponse 객체에 String 데이터를 담아 반환
    public CustomResponse<String> test(){
        return CustomResponse.onSuccess("성공");
    }

    @GetMapping("/test/failure")
    // @RequestParam 은 요청에서 특정 파라미터를 받아오는 데 사용됨
    // 여기서는 exception이라는 query 파라미터를 받아와서 int 타입으로 처리
    // 예를 들어 /test/failure?exception=1 로 요청을 보내면, exception 이라는 이름의 파라미트 값 1이 메소드에 전달됨
    // CustomResponse 객체에 String 데이터 담아 반환
    // 클라이언트가 exception 값을 0이나 1 이외의 값으로 요청하면, 아무 예외도 발생하지 않고 성공적인 응답(200 OK)이 반환
    public CustomResponse<String> failure(@RequestParam int exception) {
        if (exception == 0) {
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST_400); //400 에러
        }
        else if (exception == 1) {
            int a = 1 / 0; // 0으로 나누기 연산하면 항상 산술 예외를 발생시킴 ArithmeticException
        }
        return CustomResponse.onSuccess("에러 핸들러 동작하지 않음");
    }



}
