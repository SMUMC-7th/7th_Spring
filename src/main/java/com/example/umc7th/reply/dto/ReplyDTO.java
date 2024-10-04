package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyDTO {

    private Long id;
    private String content;

    public static ReplyDTO from(Reply reply) {
        return new ReplyDTO(reply.getId(), reply.getContent());
    }
}
