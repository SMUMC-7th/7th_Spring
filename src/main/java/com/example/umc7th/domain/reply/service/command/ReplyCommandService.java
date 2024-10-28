package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

public interface ReplyCommandService {
    ReplyResponseDTO.ResponsePreviewDto createReply(ReplyRequestDTO.CreateReplyDTO requestDTO);
    ReplyResponseDTO.ResponsePreviewDto updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto);
    void deleteReply(Long replyId);
}
