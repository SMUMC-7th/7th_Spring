package com.example.umc7th.reply.service.command;

import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;

public interface ReplyCommandService {
	Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
	Reply updateReply(Long replyId, ReplyRequestDTO.UpdateReplyDTO dto);
	void deleteReply(Long replyId);
}
