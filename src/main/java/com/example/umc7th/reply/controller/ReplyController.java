package com.example.umc7th.reply.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import com.example.umc7th.reply.converter.ReplyConverter;
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
@RequestMapping("/replies")
@Tag(name = "댓글 API")
public class ReplyController {

	private final ReplyCommandService replyCommandService;
	private final ReplyQueryService replyQueryService;

	// 댓글 생성
	@PostMapping
	@Operation(summary = "댓글 생성 API", description = "댓글 생성하는 API")
	public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
		Reply reply = replyCommandService.createReply(dto);
		return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
	}

	//댓글 하나 조회
	@GetMapping("{replyId}")
	@Operation(summary = "댓글 조회 API", description = "댓글 하나 조회하는 API")
	public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
		Reply reply = replyQueryService.getReply(replyId);
		return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
	}

	// 댓글 전체 조회
	@GetMapping
	@Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
	public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
		List<Reply> replies = replyQueryService.getReplies();
		return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
	}
}