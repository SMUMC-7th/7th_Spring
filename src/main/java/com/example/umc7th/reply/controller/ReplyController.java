package com.example.umc7th.reply.controller;

import org.springframework.data.domain.Page;
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

	// 댓글 수정
	@PutMapping("/{replyId}")
	@Operation(summary = "댓글 수정 API", description = "댓글을 수정하는 API")
	public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> updateReply(
		@PathVariable("replyId") Long replyId,
		@RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
		Reply updatedReply = replyCommandService.updateReply(replyId, dto);
		return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(updatedReply));
	}

	// 댓글 삭제
	@DeleteMapping("/{replyId}")
	@Operation(summary = "댓글 삭제 API", description = "댓글을 삭제하는 API")
	public CustomResponse<String> deleteReply(@PathVariable("replyId") Long replyId) {
		replyCommandService.deleteReply(replyId);
		return CustomResponse.onSuccess("댓글이 성공적으로 삭제되었습니다.");
	}

	// 오프셋 기반 페이지네이션: 생성 날짜 기준으로 페이지네이션
	@GetMapping("/paginated")
	@Operation(summary = "댓글 페이지네이션 조회 API", description = "오프셋 기반으로 댓글 목록을 페이지네이션하여 조회합니다.")
	public CustomResponse<Page<ReplyResponseDTO.ReplyPreviewDTO>> getRepliesWithPagination(
		@RequestParam("articleId") Long articleId,
		@RequestParam("page") int page,
		@RequestParam("size") int size) {
		Page<Reply> replies = replyQueryService.getRepliesWithPagination(articleId, page, size);
		return CustomResponse.onSuccess(replies.map(ReplyResponseDTO.ReplyPreviewDTO::from));
	}

	// 커서 기반 페이지네이션: ID 기준
	@GetMapping("/cursor-paginated")
	@Operation(summary = "댓글 커서 기반 페이지네이션 조회 API", description = "ID를 기준으로 댓글 목록을 커서 페이지네이션하여 조회합니다.")
	public CustomResponse<List<ReplyResponseDTO.ReplyPreviewDTO>> getRepliesWithCursorPagination(
		@RequestParam("articleId") Long articleId,
		@RequestParam("lastId") Long lastId,
		@RequestParam("size") int size) {
		List<Reply> replies = replyQueryService.getRepliesWithCursorPagination(articleId, lastId, size);
		return CustomResponse.onSuccess(replies.stream().map(ReplyResponseDTO.ReplyPreviewDTO::from).toList());
	}
}

