package com.example.umc7th.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.reply.dto.ReplyReqDTO;
import com.example.umc7th.reply.dto.ReplyResDTO;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reply 관련 컨트롤러")
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/reply")
    @Operation(summary = "reply 생성하는 API", description = "reply를 저장하는 용도이며 저장한 내용 반환")
    public CustomResponse<ReplyReqDTO> createReply(@RequestBody ReplyReqDTO replyReqDTO) {
        replyCommandService.saveReply(replyReqDTO);
        return CustomResponse.onSuccess(replyReqDTO);
    }

    @GetMapping("/reply/{articleId}")
    @Operation(summary = "특정 글의 reply들을 조회하는 API", description = "articleId를 가지고 해당 article의 모든 Replies를 조회하는 용도이며 저장된 모든 replies list 반환")
    public CustomResponse<List<ReplyResDTO>> getRepliesByArticleId(@PathVariable Long articleId) {
        List<ReplyResDTO> replyResDTO = replyQueryService.getRepliesByArticleId(articleId);

        return CustomResponse.onSuccess(replyResDTO);
    }
}
