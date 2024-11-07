package com.example.umc7th.domain.member.service.query;

import com.example.umc7th.domain.member.dto.MemberRequestDTO;
import com.example.umc7th.domain.member.dto.MemberResponseDTO;

public interface MemberQueryService {
    MemberResponseDTO.MemberTokenDTO login(MemberRequestDTO.LoginDTO dto);
}
