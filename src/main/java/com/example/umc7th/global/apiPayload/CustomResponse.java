package com.example.umc7th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess")
    private HttpStatus httpStatus;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    private T result;

    /* 기존 onSuccess 코드
    public static CustomResponse onSuccess() {
	  return new CustomResponse(true, HttpStatus.OK, HttpStatus.OK.getReasonPhrase());
	}
     */

    public <T> CustomResponse(boolean b, String string, String reasonPhrase, T result) {
    }

    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(true, HttpStatus.OK.toString(), HttpStatus.OK.getReasonPhrase(), result);
    }

    public static <T> CustomResponse<T> onFailure(String code, String message) {
        return new CustomResponse<>(false, code, message, null);
    }

}