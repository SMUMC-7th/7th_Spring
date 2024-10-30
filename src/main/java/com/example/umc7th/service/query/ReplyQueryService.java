package com.example.umc7th.service.query;

import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Reply;
import org.springframework.data.domain.Page;

public interface ReplyQueryService {
    public ReplyResponseDto.ReplyPreviewListDto getReplies();
    public ReplyResponseDto.ReplyPreviewDto getReply(Long id);

    public Page<Reply> getRepliesByArticle(int pageNum, int pageSize, long article);
}
