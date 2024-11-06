package com.example.umc7th.member.service.command;

import com.example.umc7th.global.jwt.JwtProvider;
import com.example.umc7th.member.dto.MemberRequestDTO;
import com.example.umc7th.member.dto.MemberResponseDTO;
import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResponseDTO.SignUpResponse signUp(MemberRequestDTO.SignUpDTO dto) {
        String encodedPassword = encoder.encode(dto.getPassword());
        Member member = dto.toEntity(encodedPassword);
        memberRepository.save(member);
        return MemberResponseDTO.SignUpResponse.from(member);
    }
}
