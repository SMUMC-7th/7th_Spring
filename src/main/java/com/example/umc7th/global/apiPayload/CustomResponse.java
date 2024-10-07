package com.example.umc7th.global.apiPayload;


import com.example.umc7th.global.apiPayload.code.GenenralSuccessCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"status", "code", "message", "isSuccess", "result"})
public class CustomResponse<T> {

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("isSuccess") // isSuccess라는 변수라는 것을 명시하는 Annotation
    private boolean isSuccess;

    @JsonProperty("result")
    private T result;

    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse(HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), true, result);
    }

    // 성공 시 응답을 생성하는 메서드 (결과 포함)
    public static <T> CustomResponse<T> of(GenenralSuccessCode code, T result) {
        return new CustomResponse<>(code.getStatus(), code.getCode(), code.getMessage(), true, result);
    }

    // 실패 시 응답을 생성하는 메서드
    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
        return new CustomResponse<>(status, code, message, false, result);
    }


}