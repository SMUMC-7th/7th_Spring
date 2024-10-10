package com.example.umc7th.reply.service.command;

import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;

public interface ReplyCommandService {
    Reply createReply(Long articleId, ReplyRequestDTO.CreateReplyDTO dto);

    void updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);

    void deleteReply(Long id);
}
