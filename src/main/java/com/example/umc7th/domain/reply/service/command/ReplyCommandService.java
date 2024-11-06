package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

public interface ReplyCommandService {
    ReplyResponseDTO.ReplyPreviewDto createReply(ReplyRequestDTO.CreateReplyDTO requestDTO);
    ReplyResponseDTO.ReplyPreviewDto updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto);
    void deleteReply(Long replyId);
}
