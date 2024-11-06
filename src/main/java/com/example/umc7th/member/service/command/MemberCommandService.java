package com.example.umc7th.member.service.command;

import com.example.umc7th.member.dto.MemberRequestDTO;
import com.example.umc7th.member.dto.MemberResponseDTO;
import com.example.umc7th.member.entity.Member;

public interface MemberCommandService {
    MemberResponseDTO.SignUpResponse signUp(MemberRequestDTO.SignUpDTO dto);
}
