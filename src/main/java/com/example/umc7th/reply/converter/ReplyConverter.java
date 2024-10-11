package com.example.umc7th.reply.converter;

import java.util.List;

import com.example.umc7th.article.entity.Article;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;

public class ReplyConverter {
	public static Reply toReply(ReplyRequestDTO.CreateReplyDTO dto, Article article) {
		return Reply.builder()
			.content(dto.getContent())
			.article(article)
			.build();
	}

	public static ReplyResponseDTO.CreateReplyResponseDTO toCreateReplyResponseDTO(Reply reply) {
		return ReplyResponseDTO.CreateReplyResponseDTO.builder()
			.id(reply.getId())
			.createdAt(reply.getCreatedAt())
			.build();
	}

	public static ReplyResponseDTO.ReplyPreviewDTO toReplyPreviewDTO(Reply reply) {
		return ReplyResponseDTO.ReplyPreviewDTO.builder()
			.id(reply.getId())
			.content(reply.getContent())
			.createdAt(reply.getCreatedAt())
			.updatedAt(reply.getUpdatedAt())
			.articleId(reply.getArticle().getId())
			.build();
	}

	public static ReplyResponseDTO.ReplyPreviewListDTO toReplyPreviewListDTO(List<Reply> replies) {
		return ReplyResponseDTO.ReplyPreviewListDTO.builder()
			.replies(replies.stream().map(ReplyConverter::toReplyPreviewDTO).toList())
			.build();
	}
}
