package com.example.umc7th.reply.service.query;

import com.example.umc7th.global.apiPayload.code.GeneralErrorCode;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import com.example.umc7th.reply.converter.ReplyConverter;
import com.example.umc7th.reply.dto.ReplyResponseDTO;
import com.example.umc7th.reply.entity.Reply;
import com.example.umc7th.reply.repository.ReplyRepository;
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
