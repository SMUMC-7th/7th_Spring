package com.example.umc7th.domain.member.service.query;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @Override
    public Member login(MemberRequestDTO.LoginDTO dto) {
        return memberRepository.findByEmail(dto.getEmail())
                .filter(m -> encoder.matches(dto.getPassword(), m.getPassword()))
                .orElseThrow(
                        () -> new CustomException(MemberErrorCode.NOT_FOUND)
                );
    }
}
