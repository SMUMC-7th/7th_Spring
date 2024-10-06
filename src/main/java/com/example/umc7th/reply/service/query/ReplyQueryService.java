package com.example.umc7th.reply.service.query;

import com.example.umc7th.reply.dto.ReplyResDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyQueryService {
    List<ReplyResDTO> getRepliesByArticleId(Long articleId);
}
