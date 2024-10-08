package com.example.umc7th.controller.global.apiPayload;

import com.example.umc7th.controller.global.apiPayload.code.BaseErrorCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CustomResponse<T> {
    @JsonProperty("isSuccess")
    private boolean isSuccess;
    @JsonProperty("status")
    private HttpStatus httpStatus;
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;

    /**
     * 성공 반환: 내려줄 데이터가 없을 때
     *
     * @return CustomResponse
     * @Param no param
     */
    public static CustomResponse<?> onSuccess() {
        return CustomResponse.builder()
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    /**
     * 성공, 내려줄 데이터가 있을 때.
     *
     * @param data
     * @param <T>
     * @return CustomResponse<T>
     */

    public static <T> CustomResponse<T> of(T data) {
        return CustomResponse.<T>builder()
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message(HttpStatus.OK.getReasonPhrase())
                .data(data)
                .build();

    }

    /**
     * 실패
     *
     * @param errorCode
     * @return CustomResponse
     */

    public static CustomResponse<?> fail(BaseErrorCode errorCode) {
        return CustomResponse.builder()
                .isSuccess(false)
                .httpStatus(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

}