package com.example.umc7th.global.apiPayload.code;

import com.example.umc7th.global.apiPayload.CustomResponse;
import org.springframework.http.HttpStatus;

// 오류 코드를 관리하고 일관된 방식으로 클라이언트에게 오류 응답을 제공하기 위한 인터페이스
public interface BaseErrorCode {

    // 제네릭 타입 T 사용해서 메소드가 반환할 데이터 타입 동적으로 설정
    // 어떤 데이터 타입이든 CustomResponse에 담아 응답할 수 있음
    // 오류가 발생했을 때, 클라이언트에게 그 오류에 대한 응답을 표준화된 형식으로 반환하기 위해 사용
    <T> CustomResponse<T> getResponse();

    // HTTP 상태 코드 반환하는 메소드 ex) HttpStatus.BAD_REQUEST
    HttpStatus getStatus();

    // 에러 코드를 문자열로 반환 ex) BAD_REQUEST_400
    String getCode();

    // 오류에 대한 설명 메세지 반환 ex) 잘못된 요청입니다.
    String getMessage();
}
