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
    public String content;

    public Reply toEntity(Article article){
        return Reply.builder()
                .content(content)
                .article(article)
                .build();
    }
}
