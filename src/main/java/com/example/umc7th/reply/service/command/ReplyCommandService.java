package com.example.umc7th.reply.service.command;

import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;

import java.util.Optional;

public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
}
