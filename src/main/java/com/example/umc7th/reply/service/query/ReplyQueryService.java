package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.dto.ReplyResponseDTO;

import java.util.List;

public interface ReplyQueryService {
    ReplyResponseDTO getReply(Long id);
    List<ReplyResponseDTO> getReplies();
}
