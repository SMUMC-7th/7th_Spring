package com.example.umc7th.domain.reply.service.query;

import com.example.umc7th.domain.reply.converter.ReplyConverter;
import com.example.umc7th.domain.reply.dto.ReplyResponseDTO;
import com.example.umc7th.domain.reply.repository.ReplyRepository;
import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import com.example.umc7th.domain.reply.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyQueryServiceImpl implements ReplyQueryService {

    private final ReplyRepository replyRepository;

    @Override
    public ReplyResponseDTO getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new GeneralException(GeneralErrorCode.REPLY_NOT_FOUND_404));
        return ReplyConverter.toReplyResponseDto(reply);
    }

    @Override
    public List<ReplyResponseDTO> getReplies() {
        return ReplyConverter.toReplyResponseDtoList(replyRepository.findAll());
    }
}
