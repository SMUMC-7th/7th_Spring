package com.example.umc7th.domain.member.service.command;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;

public interface MemberCommandService {
    MemberResponseDTO.MemberTokenDTO login(MemberRequestDTO.MemberLoginDTO dto);

    MemberResponseDTO.MemberTokenDTO signUp(MemberRequestDTO.MemberSignUpDTO dto);
}