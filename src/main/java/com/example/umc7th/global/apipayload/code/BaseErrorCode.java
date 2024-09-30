package com.example.umc7th.global.apipayload.code;

import com.example.umc7th.global.apipayload.CustomResponse;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus();
    String getCode();
    String getMessage();

    public default CustomResponse<Void> errorResponse(){
        return CustomResponse.onFailure(getStatus(), getCode(), getMessage());
    }

}
