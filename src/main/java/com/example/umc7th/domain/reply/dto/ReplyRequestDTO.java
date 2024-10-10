package com.example.umc7th.domain.reply.dto;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.entity.Reply;
import lombok.Getter;

public class ReplyRequestDTO {

    @Getter
    public static class CreateReplyDTO {

        private String content;
        private Long articleId;

        public Reply toReply(Article article) {
            return Reply.builder()
                    .content(this.content)
                    .article(article)
                    .build();
        }


    }
}
