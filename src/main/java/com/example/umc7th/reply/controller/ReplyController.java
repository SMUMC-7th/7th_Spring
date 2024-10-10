package com.example.umc7th.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.global.apiPayload.code.GeneralSuccessCode;
import com.example.umc7th.reply.dto.ReplyRequestDTO;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reply 관련 API")
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    //Create, Post 요청 사용
    @PostMapping("/{articleId}/replies")
    @Operation(summary = "reply 생성 API", description = "새 답글을 생성하는 API이고, 생성된 reply의 id를 반환합니다.")
    public CustomResponse<?> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto) {
        Reply reply = replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, reply.getId());
    }


    //여러개 조회
    @GetMapping("/{articleId}/replies")
    @Operation(summary = "게시글에 댓글 생성 API ", description = "게시글에 있는 답글을 조회하는 API입니다. ")
    public CustomResponse<?> getAllReplies(@PathVariable("articleId") Long articleId) {
        ReplyResponseDTO replies = new ReplyResponseDTO(replyQueryService.getReplies(articleId));
        return CustomResponse.onSuccess(GeneralSuccessCode.OK, replies);
    }
}
