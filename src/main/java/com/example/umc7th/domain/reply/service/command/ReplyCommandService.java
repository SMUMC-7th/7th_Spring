package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

public interface ReplyCommandService {
    ReplyResponseDTO.CreateReplyResponseDto createReply(ReplyRequestDTO.CreateReplyDTO requestDTO);
}
