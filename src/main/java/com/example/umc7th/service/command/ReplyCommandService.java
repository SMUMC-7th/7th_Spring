package com.example.umc7th.service.command;


import com.example.umc7th.dto.request.ReplyRequestDto;
import com.example.umc7th.dto.response.ReplyResponseDto;


public interface ReplyCommandService {
    public Long createReply(ReplyRequestDto.CreateReplyRequestDto replyRequestDto, Long articleId);

    public ReplyResponseDto.ReplyPreviewDto updateReply(Long replyId, ReplyRequestDto.UpdateReplyRequestDto dto);

    public void deleteReply(Long replyId);
}
