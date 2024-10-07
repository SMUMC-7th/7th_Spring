package com.example.umc7th.domain.reply.controller;


import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/replies")
    public CustomResponse<ReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto){
        Reply reply =  replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.ToDTO(reply));
    }

    @GetMapping("/replies/{replyID}")
    public CustomResponse<ReplyResponseDTO> getReply(@PathVariable Long id){
        Reply reply =  replyQueryService.getReply(id);
        return CustomResponse.onSuccess(ReplyResponseDTO.ToDTO(reply));
    }

    @GetMapping("/replies")
    public CustomResponse<List<ReplyResponseDTO>> getReplies(){
        List<Reply> replies =  replyQueryService.getReplies();
        return CustomResponse.onSuccess(replies.stream().map(ReplyResponseDTO::ToDTO).toList());
    }
}
