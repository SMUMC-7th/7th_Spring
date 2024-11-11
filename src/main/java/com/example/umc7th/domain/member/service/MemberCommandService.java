package com.example.umc7th.domain.member.service;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;

public interface MemberCommandService {

    MemberResponseDTO.MemberPreviewDTO createMember(MemberRequestDTO.CreateMemberDTO dto);

    MemberResponseDTO.LoginMemberResponseDTO login(MemberRequestDTO.LoginDTO dto);
}
