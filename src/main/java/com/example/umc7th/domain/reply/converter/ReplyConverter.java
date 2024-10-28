package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyConverter {
    //DTO -> Entity
    public static Reply toEntity(ReplyRequestDTO.CreateReplyDTO requestDto, Article article){
        return Reply.builder()
                .article(article)
                .content(requestDto.content())
                .active(true)
                .build();
    }

    //Entity -> DTO
    public static ReplyResponseDTO.ResponsePreviewDto from(Reply reply){
        return ReplyResponseDTO.ResponsePreviewDto.builder()
                .id(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .active(reply.isActive())
                .build();
    }

    public static ReplyResponseDTO.ResponsePagePreviewDto from(Page<Reply> replyPage){
        return ReplyResponseDTO.ResponsePagePreviewDto.builder()
                .replies(replyPage.getContent().stream()
                        .map(ReplyConverter::from)
                        .collect(Collectors.toList()))
                .numOfRows(replyPage.getSize())
                .pageNo(replyPage.getNumber())
                .totalCount(replyPage.getTotalElements())
                .build();
    }

    //Entity리스트 -> DTO리스트
    public static List<ReplyResponseDTO.ResponsePreviewDto> fromList(List<Reply> replies){
        return replies.stream()
                .map(ReplyConverter::from)
                .collect(Collectors.toList());
    }
}
