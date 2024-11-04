package com.example.umc7th.service.query;

import com.example.umc7th.converter.MemberConverter;
import com.example.umc7th.dto.response.MemberResponseDto;
import com.example.umc7th.entity.Member;
import com.example.umc7th.global.apipayload.code.MemberErrorCode;
import com.example.umc7th.global.apipayload.exception.MemberException;
import com.example.umc7th.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;
    @Override
    public MemberResponseDto.MemberPreviewDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new MemberException(MemberErrorCode.NOT_FOUND));

        return MemberConverter.from(member);
    }
}
