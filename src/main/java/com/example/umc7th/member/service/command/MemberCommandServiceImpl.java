package com.example.umc7th.member.service.command;

import com.example.umc7th.global.jwt.util.JwtProvider;
import com.example.umc7th.member.dto.MemberRequestDTO;
import com.example.umc7th.member.dto.MemberResponseDTO;
import com.example.umc7th.member.entity.Member;
import com.example.umc7th.member.exception.MemberErrorCode;
import com.example.umc7th.member.exception.MemberException;
import com.example.umc7th.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResponseDTO.MemberTokenDTO login(MemberRequestDTO.MemberLoginDTO dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() ->
                new MemberException(MemberErrorCode.NOT_FOUND));
        if (!encoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INCORRECT_PASSWORD);
        }
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }

    @Override
    public MemberResponseDTO.MemberTokenDTO signUp(MemberRequestDTO.MemberSignUpDTO dto) {
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new MemberException(MemberErrorCode.ALREADY_EXIST);
        }
        Member member = memberRepository.save(Member.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .role("ROLE_USER")
                .build());
        return MemberResponseDTO.MemberTokenDTO.builder()
                .accessToken(jwtProvider.createAccessToken(member))
                .refreshToken(jwtProvider.createRefreshToken(member))
                .build();
    }
}
