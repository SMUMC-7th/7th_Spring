package com.example.umc7th.service.query;


import com.example.umc7th.converter.ReplyConverter;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Reply;
import com.example.umc7th.global.apipayload.code.GeneralErrorCode;
import com.example.umc7th.global.apipayload.exception.GeneralException;
import com.example.umc7th.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;

    @Override
    public List<ReplyResponseDto> getReplies() {
        return ReplyConverter.fromList(replyRepository.findAll());
    }

    @Override
    public ReplyResponseDto getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new GeneralException(GeneralErrorCode.REPLY_NOT_FOUND));
        return ReplyConverter.from(reply);
    }
}
