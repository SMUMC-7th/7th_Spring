package com.example.umc7th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    @JsonProperty("result")
    private T result;

    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), true, result);
    }

    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
        return new CustomResponse<>(status, code, message, isSuccess, result);
    }

}