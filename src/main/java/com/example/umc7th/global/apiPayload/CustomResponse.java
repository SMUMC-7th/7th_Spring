package com.example.umc7th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

//생성자로 객체를 생성하는 것을 방지
@AllArgsConstructor(access = AccessLevel.PRIVATE)
//json 형식으로 줄 때 어떤 순서로, 어떤 변수들을 줄것인지 결정하는 annotation
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class CustomResponse {

    @JsonProperty("isSuccess") // isSuccess라는 변수라는 것을 명시하는 Annotation
    private boolean isSuccess;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    public static CustomResponse onSuccess() {
        return new CustomResponse(true, HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase());
    }
}
