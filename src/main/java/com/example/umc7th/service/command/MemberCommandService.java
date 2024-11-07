package com.example.umc7th.service.command;

import com.example.umc7th.dto.request.MemberRequestDto;
import com.example.umc7th.dto.response.MemberResponseDto;

public interface MemberCommandService {
    MemberResponseDto.MemberPreviewDto createMember(MemberRequestDto.CreateMemberRequestDto dto);

    MemberResponseDto.MemberPreviewDto updateMember(MemberRequestDto.UpdateMemberRequestDto dto);

    void deleteMember(Long id);

    MemberResponseDto.LoginResponseDto login(MemberRequestDto.LoginRequestDto dto);
}
