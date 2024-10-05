package com.example.umc7th.service.command;


import com.example.umc7th.dto.request.ReplyRequestDto;


public interface ReplyCommandService {
    public Long createReply(ReplyRequestDto replyRequestDto, Long articleId);

}
