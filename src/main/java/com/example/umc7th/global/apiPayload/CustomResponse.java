package com.example.umc7th.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
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

    @JsonProperty("method")
    private static String method; //객체 없이 접근할 예정이므로 static

    //결과 값 담을 변수 추가
    //결과 값은 항상 정해진 클래스 X => 제네릭 사용
    @JsonInclude(JsonInclude.Include.NON_NULL) // result 값은 null이 아닐 때만 응답에 포함시킨다.
    private T result; //T: 어느 타입도 될 수 있다.


    //onSuccess() method에 결과 값 추가
    //성공시 응답
    public static <T> CustomResponse<T> onSuccess(T result) {
        return new CustomResponse<>(true, HttpStatus.OK, "200", "Success", result);
    }

    //실패시 응답
//    public static <T> CustomResponse<T> onFailure(T result,String code, String message,HttpStatus status){
//        return new CustomResponse<>(false,code,message,result);
//    }


}
