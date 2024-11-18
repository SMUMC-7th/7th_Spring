package com.example.umc7th.openApi.exception;

import com.example.umc7th.global.apiPayload.code.BaseErrorCode;
import com.nimbusds.oauth2.sdk.GeneralException;

public class OpenApiException extends GeneralException {
	public OpenApiException(OpenApiErrorCode code) {
		super(code);
	}
}