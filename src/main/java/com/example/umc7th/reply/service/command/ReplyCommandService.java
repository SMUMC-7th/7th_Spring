package com.example.umc7th.reply.service.command;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.dto.AddReplyRequestDTO;
import com.example.umc7th.reply.entity.Reply;

public interface ReplyCommandService {

    Reply createReply(AddReplyRequestDTO dto, Article article);
}
