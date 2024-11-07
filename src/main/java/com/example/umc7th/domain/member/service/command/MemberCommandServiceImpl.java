package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.apiPayload.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    @Override
    public Member signUp(MemberRequestDTO.SignUpDTO dto) {
        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new CustomException(MemberErrorCode.EXIST_EMAIL);
        }

        Member member = Member.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role("ROLE_USER")
                .build();

        return memberRepository.save(member);
    }
}
