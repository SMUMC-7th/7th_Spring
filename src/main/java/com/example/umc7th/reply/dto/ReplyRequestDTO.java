package com.example.umc7th.reply.dto;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.entity.Reply;
import lombok.Getter;

public class ReplyRequestDTO {
    @Getter
    public static class CreateReplyDTO {
        private String content;
        private Long articleId;

        public Reply toEntity(Article article) {
            return Reply.builder()
                    .content(this.getContent())
                    .article(article)
                    .build();
        }

    }

    @Getter
    public static class UpdateReplyDTO {
        private String content;
    }

    @Getter
    public static class DeleteReplyDTO {
        private String id;
    }
}
