package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.dto.response.ReplyResDto;

import java.util.List;

public interface ReplyQueryService {

    List<ReplyResDto.CreateReplyResponseDto> getRepliesByArticle(Long articleId);
}
