package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ReplyResponseDto;

import java.util.List;

public interface ReplyQueryService {
    public List<ReplyResponseDto> getReplies();
    public ReplyResponseDto getReply(Long id);
}
