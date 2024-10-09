package com.example.umc7th.service.query;


import com.example.umc7th.converter.ReplyConverter;
import com.example.umc7th.dto.response.ReplyResponseDto;
import com.example.umc7th.entity.Reply;
import com.example.umc7th.exception.code.ReplyErrorCode;
import com.example.umc7th.exception.exception.ReplyException;
import com.example.umc7th.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReplyQueryServiceImpl implements ReplyQueryService{

    private final ReplyRepository replyRepository;

    @Override
    public ReplyResponseDto.ReplyPreviewListDto getReplies() {
        return ReplyConverter.fromList(replyRepository.findAll());
    }

    @Override
    public ReplyResponseDto.ReplyPreviewDto getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> new ReplyException(ReplyErrorCode.REPLY_NOT_FOUND));
        return ReplyConverter.from(reply);
    }
}
