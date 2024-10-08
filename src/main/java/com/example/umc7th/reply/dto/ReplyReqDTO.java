package com.example.umc7th.reply.dto;

import com.example.umc7th.article.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyReqDTO {
    private String content;
    private Long articleId;
}
