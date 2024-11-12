package com.example.umc7th.domain.member.service.OAuth2;

import com.example.umc7th.domain.member.dto.MemberResponseDTO;

public interface OAuth2Service {
    MemberResponseDTO.MemberTokenDTO login(String code);
}

