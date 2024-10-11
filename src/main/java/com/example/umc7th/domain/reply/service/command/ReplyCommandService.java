package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.article.dto.ArticleRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;

public interface ReplyCommandService {
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
    public Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);
    public Long deleteReply(Long id);
}
