package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ReplyResponseDto;

public interface ReplyQueryService {
    public ReplyResponseDto.ReplyPreviewListDto getReplies();
    public ReplyResponseDto.ReplyPreviewDto getReply(Long id);
}
