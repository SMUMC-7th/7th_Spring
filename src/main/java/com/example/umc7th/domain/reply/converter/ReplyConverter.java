package com.example.umc7th.domain.reply.converter;
import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import java.util.List;
//dto-entity 변환
public class ReplyConverter {
    //reply entity 생성하여 반환
    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();
    }

    //reply entity를 dto로 변환
    public static ReplyResponseDTO.CreateReplyResponseDTO toCreateReplyResponseDTO(Reply reply) {
        return ReplyResponseDTO.CreateReplyResponseDTO.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }
    //댓글 미리보게 할 수 있는 dto로 변환
    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .articleId(reply.getArticle().getId())
                .build();
    }
    //댓글 리스트 프리뷰로 반환
    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replies) {
        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replies(replies.stream().map(ReplyConverter::toReplyPreviewDTO).toList())
                .build();
    }



}