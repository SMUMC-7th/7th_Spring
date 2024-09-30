package com.example.umc7th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonPropertyOrder({"isSuccess", "HttpStatus", "code", "message", "result"})
public class CustomResponse<T> {

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    @JsonProperty("HttpStatus")
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

    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(true, HttpStatus.OK ,HttpStatus.OK.toString(), HttpStatus.OK.getReasonPhrase(), result);
    }

    public static <T> CustomResponse<T> onFailure(String code, String message) {
        return new CustomResponse<>(false, HttpStatus.BAD_REQUEST , code, message, null);
    }

}