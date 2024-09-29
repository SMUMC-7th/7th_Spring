package com.example.umc7th.global.apiPayload;


import com.example.umc7th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static <T> CustomResponse<T> onSuccess() {
        return new CustomResponse(true, HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(),null);
    }

    // 성공 시 응답을 생성하는 메서드 (결과 포함)
    public static <T> CustomResponse<T> of(BaseSuccessCode code, T result) {
        return new CustomResponse<>(true, code.getStatus(), code.getCode(), code.getMessage(), result);
    }

    // 실패 시 응답을 생성하는 메서드
    public static <T> CustomResponse<T> of(GeneralErrorCode code, T result) {
        return new CustomResponse<>(false, code.getStatus(), code.getCode(), code.getMessage(), result);
    }


}