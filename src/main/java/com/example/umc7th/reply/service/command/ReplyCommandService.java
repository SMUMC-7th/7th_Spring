package com.example.umc7th.reply.service.command;

import com.example.umc7th.reply.dto.ReplyReqDTO;
import com.example.umc7th.reply.entity.Reply;

import java.util.Optional;

public interface ReplyCommandService {
    public void saveReply(ReplyReqDTO replyReq);
}
