package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
import lombok.Data;

import java.util.List;

@Data
public class ReplyResponseDTO {
    List<DetailReplyResponseDTO> replies;

    public ReplyResponseDTO(List<Reply> replies) {
        this.replies
                = replies.stream()
                .map(DetailReplyResponseDTO::of)
                .toList();
    }
}
