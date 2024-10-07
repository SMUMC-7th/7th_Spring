package com.example.umc7th.global.apiPayload;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.example.umc7th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

// 생성자로 객체를 생성하는 것을 막기
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄것인지 결정하는 Annotation
@JsonPropertyOrder({"isSuccess", "status", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess") // isSuccess 라는 변수라는 것을 명시하는 Annotation
    private boolean isSuccess;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우
    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(true, HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), result);
    }

    // 성공한 경우(BaseSuccessCode 사용)
    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new CustomResponse<>(true, code.getStatus(), code.getCode(), code.getMessage(), result);
    }

    // 실패한 경우
    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
        return new CustomResponse<>(isSuccess, status, code, message, result);
    }

}