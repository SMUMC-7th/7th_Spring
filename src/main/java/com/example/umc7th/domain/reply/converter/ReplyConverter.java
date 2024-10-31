package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.request.ReplyReqDto;
import com.example.umc7th.domain.reply.dto.response.ReplyResDto;
import com.example.umc7th.domain.reply.entity.Reply;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyConverter {

    // DTO -> Entity
    public static Reply toReply(ReplyReqDto.CreateReplyRequestDto requestDto, Article article) {
        return Reply.builder()
                .article(article)
                .content(requestDto.content())
                .build();
    }

    // Entity -> DTO
    public static ReplyResDto.CreateReplyResponseDto toCreateReplyResponseDto(Reply reply) {
        return ReplyResDto.CreateReplyResponseDto.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    public static ReplyResDto.ReplyPreviewDto toReplyPreviewDTO(Reply reply) {
        return ReplyResDto.ReplyPreviewDto.builder()
                .id(reply.getId())
                .articleId(reply.getArticle().getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .build();
    }

    // Entity 리스트 -> DTO 리스트 변환
    public static ReplyResDto.ReplyPreviewListDto toReplyPreviewListDto(List<Reply> replies) {
        return ReplyResDto.ReplyPreviewListDto.builder()
                .replies(replies.stream().map(ReplyConverter::toReplyPreviewDTO).toList())
                .build();
    }

    // Page 객체 -> ReplyPreviewListDto 변환 메서드
    public static ReplyResDto.ReplyPreviewListDto toReplyPreviewListDto(Page<Reply> replyPage) {
        List<ReplyResDto.ReplyPreviewDto> replyDtos = replyPage.getContent()
                .stream()
                .map(ReplyConverter::toReplyPreviewDTO)
                .toList();

        return ReplyResDto.ReplyPreviewListDto.builder()
                .replies(replyDtos)
                .numOfRows(replyPage.getNumberOfElements())
                .pageNo(replyPage.getNumber()+1)
                .totalPage(replyPage.getTotalPages())
                .build();
    }
}
