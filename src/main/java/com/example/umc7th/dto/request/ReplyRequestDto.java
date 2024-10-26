package com.example.umc7th.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ReplyRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReplyRequestDto{
        public String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateReplyRequestDto{
        public String content;
    }
}
