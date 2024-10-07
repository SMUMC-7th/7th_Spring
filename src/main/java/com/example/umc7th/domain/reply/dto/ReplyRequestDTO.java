package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.article.entity.Article;
import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public class CreateReplyDTO {

        private String content;
        private Article article;

    }
}
