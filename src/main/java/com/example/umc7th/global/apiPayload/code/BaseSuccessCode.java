package com.example.umc7th.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
    //추후에 onSuccess()이용해서도 코드 작성해보기
}
