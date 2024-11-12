package com.example.umc7th.global.apiPayload;

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


    @JsonProperty("status")
    private final HttpStatus status;
    @JsonProperty("code")
    private final String code;
    @JsonProperty("message")
    private final String message;
    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    @JsonProperty("result")
    private final T result;//결과값 저장.

    //반환타입 앞의 <T>는 메서드의 T가 클래스의 T와는 다를 수 있게 함. static이기 때문에 메서드시그니처 사용
    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), true, result);
    }//

    public static <T> CustomResponse<T> of(BaseSuccessCode code, T result) {
        return new CustomResponse<>(code.getStatus(), code.getCode(), code.getMessage(), true, result);
    }

    //error에는 결과값이 못들어가므로 null하거나 모종의 이유로 builder사용하는 편이 나을까?
    //하지만 실패의 경우에도 값에 실패한 부분의 자세한 이유 등을 담아서 반환하는 경우도 있음
//    public static <T> CustomResponse<T> onFailure(BaseErrorCode code,T value) {
//        return nue
//        //return new CustomResponse(false, code.getStatus(), code.getCode(), code.getMessage(), value);
//    }
    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
        return new CustomResponse<>(status, code, message, isSuccess, result);
    }

}