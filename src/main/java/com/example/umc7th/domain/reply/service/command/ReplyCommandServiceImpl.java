package com.example.umc7th.domain.reply.service.command;

import com.example.umc7th.domain.reply.dto.ReplyRequestDTO;
import com.example.umc7th.domain.reply.entity.Reply;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandServiceImpl implements ReplyCommandService{
    private final ReplyRepository replyRepository;
    @Override
    public Reply createReply(ReplyRequestDTO.CreateReplyDTO dto) {
        // 데이터 베이스에 DTO로 만든 객체 저장하고 저장된 객체 반환
        return replyRepository.save(
                // Builder 패턴 사용한 메서드
                Reply.toEntity(dto)
        );
    }
}
