package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;

public interface ReplyCommandService {

    ReplyResDto.CreateReplyResponseDto createReply(ReplyReqDto.CreateReplyRequestDto requestDto);
    void updateReply(Long replyId, ReplyReqDto.UpdateReplyRequestDto requestDto);
    void deleteReply(Long replyId);
}
