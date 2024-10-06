package com.example.umc7th.reply.controller;

import com.example.umc7th.global.apiPayload.CustomResponse;
import com.example.umc7th.reply.dto.ReplyReqDTO;
import com.example.umc7th.reply.dto.ReplyResDTO;
import com.example.umc7th.reply.service.command.ReplyCommandService;
import com.example.umc7th.reply.service.query.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyCommandService replyCommandService;
    private final ReplyQueryService replyQueryService;

    @PostMapping("/reply")
    public CustomResponse<ReplyReqDTO> createReply(@RequestBody ReplyReqDTO replyReqDTO) {
        replyCommandService.saveReply(replyReqDTO);
        return CustomResponse.onSuccess(replyReqDTO);
    }

    @GetMapping("/reply/{articleId}")
    public CustomResponse<List<ReplyResDTO>> getRepliesByArticleId(@PathVariable Long articleId) {
        List<ReplyResDTO> replyResDTO = replyQueryService.getRepliesByArticleId(articleId);

        return CustomResponse.onSuccess(replyResDTO);
    }
}
