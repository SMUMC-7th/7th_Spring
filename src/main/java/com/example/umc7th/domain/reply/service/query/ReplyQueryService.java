package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.dto.response.ReplyResDto;

public interface ReplyQueryService {

    ReplyResDto.ReplyPreviewListDto getRepliesByArticle(Long articleId);
    ReplyResDto.ReplyPreviewListDto getRepliesByArticle(Long articleId, int pageNo, int pageSize);
    boolean hasReplies(Long articleId);
}
