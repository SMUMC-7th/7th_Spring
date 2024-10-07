package com.example.umc7th.reply.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.config.apiPayload.CustomResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/replies")
public class ReplyController {

	private final ReplyCommandService replyCommandService;
	private final ReplyQueryService replyQueryService;

	// 댓글 생성
	@PostMapping
	public CustomResponse<ReplyResponseDTO> createReply(
		@PathVariable Long articleId,
		@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {

		Reply reply = replyCommandService.createReply(articleId, dto);
		ReplyResponseDTO responseDto = new ReplyResponseDTO(reply);
		return CustomResponse.onSuccess(responseDto);
	}

	// 댓글 목록 조회
	@GetMapping
	public CustomResponse<List<ReplyResponseDTO>> getReplies(@PathVariable Long articleId) {
		List<Reply> replies = replyQueryService.getReplies(articleId);
		List<ReplyResponseDTO> responseDtos = replies.stream()
			.map(ReplyResponseDTO::new) // Reply 엔티티를 ReplyResponseDTO로 변환
			.collect(Collectors.toList());
		return CustomResponse.onSuccess(responseDtos);
	}
}