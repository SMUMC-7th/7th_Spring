package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.converter.MemberConverter;
import com.example.umc7th.domain.member.dto.MemberReqDTO;
import com.example.umc7th.domain.member.dto.MemberResDTO;
import com.example.umc7th.domain.member.entity.Member;
import com.example.umc7th.domain.member.exception.MemberErrorCode;
import com.example.umc7th.domain.member.exception.MemberException;
import com.example.umc7th.domain.member.repository.MemberRepository;
import com.example.umc7th.global.apiPayload.exception.GeneralException;
import com.example.umc7th.global.jwt.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public MemberResDTO.MemberPreviewDTO signup(MemberReqDTO.SignupReqDTO dto){
        if(memberRepository.existsByEmail(dto.email())){
            throw new GeneralException(MemberErrorCode.ALREADY_EXIST);
        }

        Member member = memberRepository.save(MemberConverter.toMember(dto, passwordEncoder));
        return MemberConverter.toMemberDTO(member);
    }

    @Override
    public MemberResDTO.LoginResDTO login(MemberReqDTO.LoginReqDTO dto){
        Member member = memberRepository.findByEmail(dto.email()).orElseThrow(
                ()-> new MemberException(MemberErrorCode.NOT_FOUND));

        if (!passwordEncoder.matches(dto.password(), member.getPassword())){
            throw new GeneralException(MemberErrorCode.INCORRECT_PASSWORD);
        }

        String accessToken = jwtProvider.createAccessToken(member);
        String refreshTocken = jwtProvider.createRefreshToken(member);
        return MemberConverter.toLoginDTO(member, accessToken, refreshTocken);
    }
}
