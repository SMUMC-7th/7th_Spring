package com.example.umc7th.global.apiPayload;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

//생성자로 객체를 생성하는 것을 방지
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄것인지 결정하는 annotation
@JsonPropertyOrder({"isSuccess", "status", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("status")
    private final HttpStatus status;
    @JsonProperty("code")
    private final String code;
    @JsonProperty("message")
    private final String message;
    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    @JsonProperty("result")
    private final T result;

    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), true, result);
    }

    public static <T> CustomResponse<T> of(BaseSuccessCode code, T result) {
        return new CustomResponse<>(code.getStatus(), code.getCode(), code.getMessage(), true, result);
    }

    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
        return new CustomResponse<>(status, code, message, isSuccess, result);
    }
}
