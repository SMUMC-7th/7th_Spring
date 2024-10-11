package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

public interface ReplyCommandService {

    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);

    ReplyResponseDTO.UpdateReplyResponseDTO updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);

    void deleteReply(Long id);
}
