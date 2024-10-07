package com.example.umc7th.global.config.apiPayload.exception;

import com.example.umc7th.global.config.apiPayload.CustomResponse;
import com.example.umc7th.global.config.apiPayload.code.BaseErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	public BaseErrorCode code;
	public CustomException(BaseErrorCode code){
		this.code = code;

	}
}
