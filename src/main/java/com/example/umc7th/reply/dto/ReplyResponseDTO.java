package com.example.umc7th.reply.dto;

import com.example.umc7th.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReplyResponseDTO {
    List<DetailReplyResponseDTO> replies;

    public static ReplyResponseDTO from(List<Reply> replies) {
        return new ReplyResponseDTO(replies.stream()
                .map(DetailReplyResponseDTO::of)
                .toList());
    }
}
