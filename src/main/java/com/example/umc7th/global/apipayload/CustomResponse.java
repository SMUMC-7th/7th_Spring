package com.example.umc7th.global.apipayload;


import com.example.umc7th.global.apipayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonProperty("isSuccess") // isSuccess라는 변수라는 것을 명시하는 Annotation
    private boolean isSuccess;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("result")
    @JsonInclude(JsonInclude.Include.NON_NULL)   //
    private T result;


    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse(true, HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), result);
    }
    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new CustomResponse(true, HttpStatus.OK, code.getCode(), HttpStatus.OK.getReasonPhrase(), result);
    }
    // result 없는 성공
    public static <T> CustomResponse<T> onSuccess(BaseSuccessCode code) {
        return new CustomResponse(true, HttpStatus.OK, code.getCode(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, T result){
        return new CustomResponse(false, status, code, message, result);
    }
    // result 없는 실패
    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message){
        return new CustomResponse(false, status, code, message, null);
    }
}
