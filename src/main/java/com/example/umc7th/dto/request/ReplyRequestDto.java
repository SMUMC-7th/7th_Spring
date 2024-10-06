package com.example.umc7th.dto.request;


import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReplyRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReplyRequestDto{
        public String content;
    }
}
