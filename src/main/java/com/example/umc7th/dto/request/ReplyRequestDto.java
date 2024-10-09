package com.example.umc7th.dto.request;


import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
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
