package com.example.umc7th.global.config.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
	HttpStatus getStatus();
	String getCode();
	String getMessage();
}