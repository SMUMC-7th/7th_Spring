package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;

// reply command( 생성, 수정, 삭제 ) 를 위한 서비스 인터페이스
public interface ReplyCommandService {
    Reply createReply(ReplyRequestDTO.CreateReplyDTO dto);
    Reply updateReply(Long id, ReplyRequestDTO.UpdateReplyDTO dto);
    Reply deleteReply(Long id);
}