package com.example.umc7th.global.apiPayload;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE) // 객체 생성하는 것 막기
@JsonPropertyOrder({"isSuccess", "status", "code", "message", "result"}) // json 형식으로 줄 때 어떤 순서, 변수들을 줄 것인가 결정
public class CustomResponse<T> { // 제너릭으로 표현

    @JsonProperty("isSuccess") //변수 명시해주는 어노테이션
    private boolean isSuccess;

    @JsonProperty("status")
    private HttpStatus httpStatus;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new CustomResponse<>(true, code.getStatus(), code.getCode(), code.getMessage(), result);
    }

    public static <T> CustomResponse<T> onFailure(BaseErrorCode code) {
        return new CustomResponse<>(false, code.getStatus(), code.getCode(), code.getMessage(), null);
    }

}

