package com.example.umc7th.converter;

import com.example.umc7th.dto.request.ReplyRequestDto;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Article;
import com.example.umc7th.entity.Reply;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReplyConverter {
    public static Reply toEntity(ReplyRequestDto.CreateReplyRequestDto dto, Article article){
        return Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();
    }

    public static ReplyResponseDto.ReplyPreviewDto from(Reply reply){
        return ReplyResponseDto.ReplyPreviewDto.builder()
                .id(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreated_at())
                .updatedAt(reply.getUpdated_at())
                .build();
    }

    public static ReplyResponseDto.ReplyPreviewListDto fromList(List<Reply> replies) {
        return ReplyResponseDto.ReplyPreviewListDto.builder()
                .replies(replies.stream().map(ReplyConverter::from).toList())
                .build();
    }

    public static ReplyResponseDto.ReplyPagePreviewListDto from(Page<Reply> page) {
        return ReplyResponseDto.ReplyPagePreviewListDto.builder()
                .replyPreviewDtoList(page.getContent().stream().map(ReplyConverter::from).toList())
                .numOfRows(page.getSize())
                .pageNo(page.getNumber())
                .totalCount(page.getTotalElements())
                .build();
    }
}
