package com.example.umc7th.domain.reply.controller;


import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}") //공통으로 빼기
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    //댓글 생성
    @PostMapping("/replies")
    @Operation(summary = "댓글 생성 API", description = "댓글 생성")
    public CustomResponse<ReplyResponseDTO.CreateReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyConverter.toCreateReplyResponseDTO(reply));
    }

    //해당 게시물의 모든 댓글 조회
    @GetMapping("/replies")
    @Operation(summary = "댓글 전체 조회 API", description = "댓글 전체 조회하는 API")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewListDTO> getReplies() {
        List<Reply> replies = replyQueryService.getReplies();
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewListDTO(replies));
    }

    //댓글 단일 조회
    @GetMapping("/replies/{replyId}")
    @Operation(summary = "댓글 단일 조회 API", description = "댓글 단일 조회")
    public CustomResponse<ReplyResponseDTO.ReplyPreviewDTO> getReply(@PathVariable("replyId") Long replyId) {
        Reply reply = replyQueryService.getReply(replyId);
        return CustomResponse.onSuccess(ReplyConverter.toReplyPreviewDTO(reply));
    }

    //댓글 수정
    @PutMapping("/replies/{replyId}")
    @Operation(summary = "댓글 수정 API", description = "댓글 수정")
    public CustomResponse<?> updateReply(@PathVariable Long replyId, @RequestBody ReplyRequestDTO.UpdateReplyDTO dto) {
        replyCommandService.updateReply(replyId, dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK);
    }

    //댓글 삭제
    @DeleteMapping("/replies/{replyId}")
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제")
    public CustomResponse<Void> deleteReply(@PathVariable Long replyId){
        replyCommandService.deleteReply(replyId);
        return CustomResponse.onSuccess(null); //converter 사용 안 함
    }


}
