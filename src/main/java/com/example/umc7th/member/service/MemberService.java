package com.example.umc7th.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.umc7th.global.jwt.JwtProvider;
import com.example.umc7th.member.dto.MemberDto;
import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.exception.MemberErrorCode;
import com.example.umc7th.member.exception.MemberException;
import com.example.umc7th.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public void signup(MemberDto.SignupRequest request) {
        // 비밀번호 인코딩
        String encodedPassword = encoder.encode(request.getPassword());

        // 회원 생성 및 저장
        Member member = new Member(request.getEmail(), encodedPassword, request.getName(), "ROLE_USER");
        memberRepository.save(member);
    }

    public String login(MemberDto.LoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        // 로그인 성공 시 JWT 발급
        return jwtProvider.createAccessToken(member);
    }
}