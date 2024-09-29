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

    @JsonProperty("isSuccess") // isSuccess라는 변수라는 것을 명시하는 Annotation
    private boolean isSuccess;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new CustomResponse<>(true, code.getStatus(), code.getCode(), code.getMessage(), result);
    }

    public static <T> CustomResponse<T> onFailure(BaseErrorCode code, T result) {
        return new CustomResponse<>(false, code.getStatus(), code.getCode(), code.getMessage(), result);
    }
}
