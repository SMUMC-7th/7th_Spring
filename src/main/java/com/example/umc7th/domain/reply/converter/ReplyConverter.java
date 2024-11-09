package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import org.springframework.data.domain.Page;

/** Reply 엔티티와 DTO 간의 변환을 담당하여, 서비스 로직에서 DTO를 생성하고 반환할 수 있도록 도와줌 */
public class ReplyConverter {

    // CreateReplyDTO와 Article 객체를 받아 Reply 엔티티로 변환
    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();
    }

    // Reply 엔티티를 CreateReplyResponseDTO로 변환
    public static ReplyResponseDTO.CreateReplyResponseDTO toCreateReplyResponseDTO(Reply reply) {
        return ReplyResponseDTO.CreateReplyResponseDTO.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    // Reply 엔티티를 ReplyPreviewDTO로 변환
    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .articleId(reply.getArticle().getId())
                .build();
    }
    // Page 객체의 Reply 엔티티들을 ReplyPreviewDTO 리스트로 변환하고 페이지 정보 설정
    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(Page<Reply> replies) {
        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replies(replies.getContent().stream().map(ReplyConverter::toReplyPreviewDTO).toList())
                .pageNo(replies.getNumber() + 1)
                .size(replies.getSize())
                .totalPage(replies.getTotalPages())
                .build();
    }
}