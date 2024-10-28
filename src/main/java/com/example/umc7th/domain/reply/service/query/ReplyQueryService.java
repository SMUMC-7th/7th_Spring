package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReplyQueryService {
    List<ReplyResponseDTO.ResponsePreviewDto> getRepliesByArticle(Long articleId);
    ReplyResponseDTO.ResponsePagePreviewDto getRepliesByArticleId(Long articleId, int pageNo, int pageSize);
}

