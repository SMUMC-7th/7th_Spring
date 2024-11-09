package com.example.umc7th.domain.reply.converter;

import com.example.umc7th.domain.article.entity.Article;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;

import java.util.List;

/**
 * 댓글 관련 DTO와 엔티티 간의 변환을 담당하는 클래스
 * article처럼 dto내에서 변환해도 되고 이렇게 분리해도 된다.
 */
public class ReplyConverter {

    /**
     * 생성된 댓글 DTO를 댓글 엔티티로 변환
     * @param dto 댓글 생성 정보 DTO
     * @param article 댓글이 속할 게시글 엔티티
     * @return 변환된 댓글 엔티티
     */
    public static Reply toReply(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
        return Reply.builder()
                .content(dto.getContent())
                .article(article)
                .build();
    }

    /**
     * 댓글 엔티티를 댓글 생성 응답 DTO로 변환
     * @param reply 댓글 엔티티
     * @return 변환된 댓글 생성 응답 DTO
     */
    public static ReplyResponseDTO.CreateReplyResponseDTO toCreateReplyResponseDTO(Reply reply) {
        return ReplyResponseDTO.CreateReplyResponseDTO.builder()
                .id(reply.getId())
                .createdAt(reply.getCreatedAt())
                .build();
    }

    /**
     * 댓글 엔티티를 댓글 preview DTO로 변환
     * @param reply 댓글 엔티티
     * @return 변환된 댓글 preview DTO
     */
    public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
        return ReplyResponseDTO.ReplyPreviewDTO.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createdAt(reply.getCreatedAt())
                .updatedAt(reply.getUpdatedAt())
                .articleId(reply.getArticle().getId())
                .build();
    }

    /**
     * 댓글 리스트를 댓글 preview 리스트 DTO로 변환
     * @param replies 댓글 리스트
     * @return 변환된 댓글 preview 리스트 DTO
     */
    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replies) {
        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replies(replies.stream().map(ReplyConverter::toReplyPreviewDTO).toList())
                .build();
    }

    /**
     * 댓글 삭제 ID를 이용해 댓글 삭제 응답 DTO로 변환
     * @param replyId 삭제된 댓글 ID
     * @return 변환된 댓글 삭제 응답 DTO
     */
    public static ReplyResponseDTO.DeleteReplyResponseDTO toDeleteReplyResponseDTO(Long replyId) {
        return new ReplyResponseDTO.DeleteReplyResponseDTO(replyId, "댓글 삭제가 성공적으로 완료되었습니다.");
    }

    /**
     * 댓글 리스트와 페이지 정보를 이용해 댓글 리스트 DTO로 변환
     * @param replies 댓글 리스트
     * @param numOfRows 한 페이지의 댓글 수
     * @param pageNo 현재 페이지 번호
     * @param totalCount 총 댓글 수
     * @return 변환된 댓글 미리보기 리스트 DTO
     */
    public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replies, int numOfRows, int pageNo, int totalCount) {
        return ReplyResponseDTO.ReplyPreviewListDTO.builder()
                .replies(replies.stream().map(ReplyConverter::toReplyPreviewDTO).toList())
                .numOfRows(numOfRows)
                .pageNo(pageNo)
                .totalCount(totalCount)
                .build();
    }
}