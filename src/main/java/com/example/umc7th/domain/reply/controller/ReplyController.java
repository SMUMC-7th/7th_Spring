package com.example.umc7th.domain.reply.controller;


import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.service.command.ReplyCommandService;
import com.example.umc7th.domain.reply.service.query.ReplyQueryService;
import com.example.umc7th.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "댓글 API")
@RestController
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyQueryService replyQueryService;
    private final ReplyCommandService replyCommandService;

    @PostMapping("/articles/replies")
    @Operation(method = "POST", summary = "댓글 작성 API")
    public CustomResponse<ReplyResponseDTO> createReply(@RequestBody ReplyRequestDTO.CreateReplyDTO dto){
        Reply reply =  replyCommandService.createReply(dto);
        return CustomResponse.onSuccess(ReplyResponseDTO.ToDTO(reply));
    }


    @GetMapping("/articles/{ArticleID}/replies")
    @Operation(method = "POST", summary = "게시글별 검색 API")
    public CustomResponse<List<ReplyResponseDTO>> getRepliesByArticle(Long articleId){
        List<Reply> replies =  replyQueryService.getReplies(articleId);
        return CustomResponse.onSuccess(replies.stream().map(ReplyResponseDTO::ToDTO).toList());
    }
}
