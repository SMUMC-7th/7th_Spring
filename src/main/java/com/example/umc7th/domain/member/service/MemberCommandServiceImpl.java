package com.example.umc7th.domain.member.service;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    @Override
    public MemberResponseDTO.MemberPreviewDTO createMember(MemberRequestDTO.MemberSignUpDTO dto) {

        Member savedMember = memberRepository.save(dto.toEntity(passwordEncoder));
        return MemberResponseDTO.MemberPreviewDTO.from(savedMember);
    }

    @Override
    public MemberResponseDTO.LoginMemberResponseDTO login(MemberRequestDTO.MemberLoginDTO dto) {

        Member logedMember = memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new MemberException(MemberErrorCode.NOT_FOUND)
        );

        String accessToken = jwtProvider.createAccessToken(logedMember);
        String refreshToken = jwtProvider.createRefreshToken(logedMember);

        return MemberResponseDTO.LoginMemberResponseDTO.from(logedMember, accessToken, refreshToken);
    }

}
