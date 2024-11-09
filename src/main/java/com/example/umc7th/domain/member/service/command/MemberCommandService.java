package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.request.MemberReqDto;
import com.example.umc7th.domain.member.dto.response.MemberResDto;

public interface MemberCommandService {

    MemberResDto.MemberPreviewDto createMember(MemberReqDto.CreateMemberRequestDto dto);

    MemberResDto.LoginResponseDto login(MemberReqDto.LoginRequestDto dto);
}
