package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.converter.MemberConverter;
import com.example.umc7th.domain.member.dto.request.MemberReqDto;
import com.example.umc7th.domain.member.dto.response.MemberResDto;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResDto.MemberPreviewDto createMember(MemberReqDto.CreateMemberRequestDto requestDto) {
        Member member = memberRepository.save(MemberConverter.toMember(requestDto, passwordEncoder));
        return MemberConverter.toMemberPreviewDto(member);
    }

    @Override
    public MemberResDto.LoginResponseDto login(MemberReqDto.LoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.email()).orElseThrow(
                ()-> new MemberException(MemberErrorCode.NOT_FOUND));
        String accessToken = jwtProvider.createAccessToken(member);
        String refreshToken = jwtProvider.createRefreshToken(member);
        return MemberConverter.toLoginResponseDto(member, accessToken, refreshToken);
    }
}
