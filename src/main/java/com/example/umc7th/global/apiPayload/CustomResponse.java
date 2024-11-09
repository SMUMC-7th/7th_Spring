package com.example.umc7th.global.apiPayload;

import com.example.umc7th.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * API 응답으로 사용되는 custom response 를 정의한 코드
 * 이 클래스로 일관된 응답 포맷을 제공하고, 성공 또는 실패에 따른 응답 반환 가능
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE) // 접근 수준을 PRIVATE으로 설정하여 외부에서 직접 인스턴스를 생성할 수 없도록 함.
// JSON 직렬화 시 필드의 출력 순서 지정
// Jackson 라이브러리를 사용할 때 응답 객체의 필드들이 순서대로 클라이언트에 전달되도록 설정
@JsonPropertyOrder({"status", "code", "message", "isSuccess", "result"})
public class CustomResponse<T> {

    // 응답 객체에서 'status' 필드가 JSON 직렬화될 때, 'status' 라는 이름으로 표시되도록
    @JsonProperty("status")
    private HttpStatus status; // 응답 상태

    @JsonProperty("code")
    private String code; // 응답 코드

    @JsonProperty("message")
    private String message; // 응답 메세지

    @JsonProperty("isSuccess")
    private boolean isSuccess; // 성공 여부를 나타내는 플래그 (true, false)

    @JsonProperty("result")
    private T result; // 응답에 포함될 실제 데이터

    // 성공 응답 생성
    // status : HttpStatus.OK
    // code : String.valueOf(HttpStatus.OK.value())
    // message : HttpStatus.OK.getReasonPhrase()
    // isSuccess : true
    // result : result
    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(HttpStatus.OK, String.valueOf(HttpStatus.OK.value()), HttpStatus.OK.getReasonPhrase(), true, result);
    }

    /** BaseSuccessCode를 사용하여 성공 응답 생성 */
    // 이 방법이 가장 선호되는 방법 !
    // 다양한 에러 상황을 코드와 메세지로 정의할 수 있어서 유지보수 확장성 뛰어남
    // status : code.getStatus()
    // code : code.getCode()
    // message : code.getMessage()
    // isSuccess : true
    // result : result
    public static <T> CustomResponse<T> of(BaseSuccessCode code, T result) {
        return new CustomResponse<>(code.getStatus(), code.getCode(), code.getMessage(), true, result);
    }

    // 실패 응답 생성
    // status : status
    // code :  code
    // message : message
    // isSuccess : isSuccess
    // result : result
    public static <T> CustomResponse<T> onFailure(HttpStatus status, String code, String message, boolean isSuccess, T result) {
        return new CustomResponse<>(status, code, message, isSuccess, result);
    }

}